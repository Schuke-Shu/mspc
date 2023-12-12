package cn.mabbit.mspc.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>日志注解</h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-25 10:33
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface Log // TODO 日志处理
{
    /**
     * @return 标题
     */
    String title() default "log";

    /**
     * @return 描述
     */
    String description() default "";

    /**
     * @return 操作类型
     */
    OperationType operation();

    /**
     * 操作类型
     */
    enum OperationType
    {
        SAVE,
        REMOVE,
        EDIT,
        QUERY,
    }
}