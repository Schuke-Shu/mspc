package cn.mabbit.mspc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>与 PostConstruct 效果相同</p>
 *
 * @Date 2023/12/28 19:37
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Initialize {}