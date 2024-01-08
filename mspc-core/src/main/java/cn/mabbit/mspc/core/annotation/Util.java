package cn.mabbit.mspc.core.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>工具组件标记</h2>
 *
 * @Date 2024/1/4 17:55
 */
@Component
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Util
{
    @AliasFor(annotation = Component.class)
    String value() default "";
}