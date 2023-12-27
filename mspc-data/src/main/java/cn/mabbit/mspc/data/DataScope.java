package cn.mabbit.mspc.data;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>数据范围注解</h2>
 *
 * @Date 2023/12/25 10:33
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * @return 受检字段名
     */
    String checkedField() default "";

    /**
     * @return 限制字段名
     */
    String acceptField() default "";

    /**
     * @return 接口权限字符，默认从权限注解中获取
     */
    String[] permission() default {};
}