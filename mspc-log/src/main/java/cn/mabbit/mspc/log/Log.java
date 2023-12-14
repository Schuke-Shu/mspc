package cn.mabbit.mspc.log;

import cn.mabbit.mspc.log.enums.BusinessType;
import cn.mabbit.mspc.log.enums.OperatorType;

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
public @interface Log
{
    /**
     * @return 业务模块
     */
    String title() default "";

    /**
     * @return 描述
     */
    String description() default "";

    BusinessType businessType() default BusinessType.OTHER;

    /**
     * @return 操作类型
     */
    OperatorType operation() default OperatorType.ADMIN;
}