package cn.mabbit.mspc.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <h2>默认全局异常处理器</h2>
 *
 * <p>不需要扩展异常处理器时可以不自己创建全局异常处理器</p>
 *
 * @Date 2023-11-28 11:06
 */
@RestControllerAdvice
@ConditionalOnMissingBean(AbstractExceptionHandler.class)
public class DefaultGlobalExceptionHandler
        extends AbstractExceptionHandler
{
    public DefaultGlobalExceptionHandler()
    {
        super();
        log.info("没有发现全局异常处理器，创建默认处理器");
    }
}