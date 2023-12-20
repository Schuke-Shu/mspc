package cn.mabbit.mspc.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * <h2>异常基类</h2>
 *
 * @author 一只枫兔
 * @Date 2023/10/09 18:15
 */
@Getter
@Setter
public abstract class BaseException
        extends RuntimeException
{
    /**
     * 细节信息，用于内部调试
     */
    protected String detail;

    protected BaseException() {}

    protected BaseException(String message)
    {
        super(message);
    }

    protected BaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    protected BaseException(Throwable cause)
    {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}