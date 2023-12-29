package cn.mabbit.mspc.data;

import cn.mabbit.mspc.annotation.Initialize;
import cn.mabbit.mspc.annotation.Load;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <h2>数据库模块配置</h2>
 *
 * @Date 2023-12-12 8:35
 */
@Slf4j
@Configuration
@MapperScan("**.mapper")
@Setter(onMethod_ = @Load)
public class DataConfig
{
    public DataConfig()
    {
        log.info("开始进行数据库模块配置");
    }

    private List<SqlSessionFactory> factories;

    // 配置 Mybatis 拦截器
    @Initialize
    public void addInterceptor()
    {
        log.debug("配置 Mybatis 拦截器");
        TimeInterceptor interceptor = new TimeInterceptor();
        for (SqlSessionFactory factory : factories)
            factory
                    .getConfiguration()
                    .addInterceptor(interceptor);
    }
}