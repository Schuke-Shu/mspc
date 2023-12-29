package cn.mabbit.mspc.annotation;

import java.lang.annotation.*;

/**
 * <p>与 Autowired 效果相同</p>
 *
 * @Date 2023/12/28 19:57
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Load
{
    boolean required() default true;
}