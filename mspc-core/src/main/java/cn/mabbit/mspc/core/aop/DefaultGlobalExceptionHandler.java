package cn.mabbit.mspc.core.aop;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <h2>默认全局异常处理器</h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-28 11:06
 */
@RestControllerAdvice
@ConditionalOnMissingBean(AbstractExceptionHandler.class)
public class DefaultGlobalExceptionHandler
        extends AbstractExceptionHandler
{}