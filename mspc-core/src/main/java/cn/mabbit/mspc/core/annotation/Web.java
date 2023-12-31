package cn.mabbit.mspc.core.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>业务模块注解</h2>
 *
 * <p>标记 controller 层的类</p>
 *
 * <p>组合了 {@link Validated}、{@link RestController}、{@link RequestMapping}</p>
 *
 * @Date 2023-11-03 10:01
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Validated
@RestController
@RequestMapping
public @interface Web
{
    @AliasFor(annotation = RestController.class)
    String value() default "";

    @AliasFor(annotation = RequestMapping.class)
    String path() default "";

    @AliasFor(annotation = RequestMapping.class)
    String name() default "";
}