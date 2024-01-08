package cn.mabbit.mspc.data;

import cn.mabbit.mspc.core.BaseRequestContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h2>基于 MyBatis 的自动插入时间拦截器</h2>
 *
 * <p>用于设置创建时间与最后修改时间</p>
 *
 * <p>注意：由于仅适用于当前项目，并不具备范用性，所以：</p>
 *
 * <ul>
 * <li>拦截所有的 update 方法（根据 SQL 语句以 update 前缀进行判定），无法不拦截某些 update 方法</li>
 * <li>所有数据表中"最后修改时间"的字段名必须一致，由本拦截器的FIELD_MODIFIED属性进行设置</li>
 * </ul>
 *
 * @Date 2023-12-12 11:07
 */
@Slf4j
@Intercepts(
        {
                @Signature(
                        type = StatementHandler.class,
                        method = "prepare",
                        args = {Connection.class, Integer.class}
                )
        }
)
public class TimeInterceptor
        implements Interceptor
{
    public static final String SQL_FIELD = "sql";

    /**
     * 自动添加的创建时间字段
     */
    private static final String FIELD_CREATE = "create_time";
    /**
     * 自动更新时间的字段
     */
    private static final String FIELD_MODIFIED = "modified_time";
    /**
     * 查找SQL类型的正则表达式：INSERT
     */
    private static final Pattern SQL_TYPE_PATTERN_INSERT = Pattern.compile("^insert\\s", Pattern.CASE_INSENSITIVE);
    /**
     * 查找SQL类型的正则表达式：UPDATE
     */
    private static final Pattern SQL_TYPE_PATTERN_UPDATE = Pattern.compile("^update\\s", Pattern.CASE_INSENSITIVE);
    /**
     * 查询SQL语句片段的正则表达式：modified_time片段
     */
    private static final Pattern SQL_STATEMENT_PATTERN_MODIFIED = Pattern.compile(
            ",\\s*" + FIELD_MODIFIED + "\\s*=", Pattern.CASE_INSENSITIVE
    );
    /**
     * 查询SQL语句片段的正则表达式：create_time片段
     */
    private static final Pattern SQL_STATEMENT_PATTERN_CREATE = Pattern.compile(
            ",\\s*" + FIELD_CREATE + "\\s*[,)]?", Pattern.CASE_INSENSITIVE
    );
    /**
     * 查询SQL语句片段的正则表达式：WHERE子句
     */
    private static final Pattern SQL_STATEMENT_PATTERN_WHERE = Pattern.compile(
            "\\s+where\\s+", Pattern.CASE_INSENSITIVE
    );
    /**
     * 查询SQL语句片段的正则表达式：VALUES子句
     */
    private static final Pattern SQL_STATEMENT_PATTERN_VALUES = Pattern.compile(
            "\\)\\s*values?\\s*\\(", Pattern.CASE_INSENSITIVE
    );

    @Override
    public Object intercept(Invocation invocation)
            throws Throwable
    {
        // 获取 BoundSql
        log.debug("准备拦截 sql");
        BoundSql boundSql = getBoundSql(invocation);

        String sql = getSql(boundSql);
        log.debug("原 sql，{}", sql);

        // 生成新 sql
        String newSql = null;
        if (SQL_TYPE_PATTERN_INSERT.matcher(sql).find()) newSql = makeCreateSql(sql);
        if (SQL_TYPE_PATTERN_UPDATE.matcher(sql).find()) newSql = makeModifiedSql(sql);

        if (newSql != null)
        {
            log.debug("新 sql：{}", newSql);
            reflect(boundSql, newSql); // TODO Mdk4j 反射工具
        }

        // 放行
        return invocation.proceed();
    }

    private String makeCreateSql(String oldSql)
    {
        log.debug("原 sql 类型为【INSERT】，准备添加 create_time 字段");

        if (SQL_STATEMENT_PATTERN_CREATE.matcher(oldSql).find())
        {
            log.debug("原 sql 已经包含 create_time 字段");
            return null;
        }

        // INSERT into table (xx, xx, xx ) values (?,?,?)
        // 查找 ) values ( 的位置
        StringBuilder sql = new StringBuilder(oldSql);
        Matcher valuesClauseMatcher = SQL_STATEMENT_PATTERN_VALUES.matcher(sql);
        if (!valuesClauseMatcher.find())
        {
            log.warn("没有找到 【) values (】 段");
            return null;
        }

        int start = valuesClauseMatcher.start();
        int end = valuesClauseMatcher.end();

        // 插入字段列表
        String fieldNames = ", " + FIELD_CREATE;
        sql.insert(start, fieldNames);

        // 定义查找参数值位置的 正则表达 “)”
        Matcher paramPositionMatcher = Pattern.compile("\\)").matcher(sql);
        // 从 ) values ( 的后面位置 end 开始查找 结束括号的位置
        String param = ", '" + BaseRequestContextHandler.requestTime() + "'";
        int position = end + fieldNames.length();
        while (paramPositionMatcher.find(position))
        {
            start = paramPositionMatcher.start();
            end = paramPositionMatcher.end();
            sql.insert(start, param);
            position = end + param.length();
        }

        if (position == end)
        {
            log.warn("没有找到 sql 插入位置！");
            return null;
        }

        return sql.toString();
    }

    private String makeModifiedSql(String oldSql)
    {
        log.debug("原 sql 类型为【UPDATE】，准备添加 modified_time 字段");

        if (SQL_STATEMENT_PATTERN_MODIFIED.matcher(oldSql).find())
        {
            log.debug("原 sql 已经包含 modified_time 字段");
            return null;
        }

        StringBuilder sql = new StringBuilder(oldSql);
        Matcher matcher = SQL_STATEMENT_PATTERN_WHERE.matcher(sql);
        // 查找 where 子句的位置
        if (!matcher.find()) return null;
        sql.insert(matcher.start(), ", " + FIELD_MODIFIED + "='" + BaseRequestContextHandler.requestTime() + "'");

        return sql.toString();
    }

    @Override
    public Object plugin(Object target)
    {
        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties)
    {
        // ignored
    }

    /**
     * @param invocation 调用对象
     * @return {@link BoundSql}
     */
    private BoundSql getBoundSql(Invocation invocation)
    {
        Object target = invocation.getTarget();
        if (target instanceof StatementHandler handler)
            return handler.getBoundSql();

        throw new RuntimeException("没有找到【StatementHandler】！请检查拦截器配置！");
    }

    /**
     * 从 BoundSql 对象中获取 SQL 语句
     *
     * @param boundSql {@link BoundSql}
     * @return 将 BoundSql 对象中封装的 SQL 语句进行转换小写、去除多余空白后的 SQL 语句
     */
    private String getSql(BoundSql boundSql)
    {
        return boundSql.getSql().toLowerCase().replaceAll("\\s+", " ").trim();
    }

    /**
     * 更换 {@link BoundSql} 中 sql 属性的值
     *
     * @param sql   需要设置值的对象
     * @param value 新的值
     * @throws NoSuchFieldException   无此字段异常
     * @throws IllegalAccessException 非法访问异常
     */
    private void reflect(BoundSql sql, String value)
            throws NoSuchFieldException, IllegalAccessException
    {
        Field field = BoundSql.class.getDeclaredField(SQL_FIELD);
        field.setAccessible(true);
        field.set(sql, value);
    }
}