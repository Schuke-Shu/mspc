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

    public BaseException() {}

    public BaseException(String message)
    {
        super(message);
    }

    public BaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BaseException(Throwable cause)
    {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}