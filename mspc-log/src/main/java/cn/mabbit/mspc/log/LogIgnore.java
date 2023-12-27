package cn.mabbit.mspc.log;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>日志序列化排除注解</h2>
 *
 * <p>标记一个类或属性，在打印日志时排除该元素</p>
 *
 * <p>标注在 {@link Log} 标记的方法中的参数、参数对象内部属性、返回值对象内部属性上</p>
 *
 * @Date 2023/12/27 19:29
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface LogIgnore{}