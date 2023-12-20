package cn.mabbit.mspc.core.exception;

import java.io.Serial;

/**
 * <h2>全局项目异常</h2>
 *
 * @author 一只枫兔
 * @Date 2023/10/09 18:15
 */
public class ProjectException
        extends BaseException
{
    @Serial
    private static final long serialVersionUID = 1L;

    protected ProjectException() {}

    protected ProjectException(String message)
    {
        super(message);
    }

    protected ProjectException(String message, Throwable cause)
    {
        super(message, cause);
    }

    protected ProjectException(Throwable cause)
    {
        super(cause);
    }

    protected ProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static ProjectException _new()
    {
        return new ProjectException();
    }

    public static ProjectException _new(String message)
    {
        return new ProjectException(message);
    }

    public static ProjectException _new(String message, Throwable cause)
    {
        return new ProjectException(message, cause);
    }

    public static ProjectException _new(Throwable cause)
    {
        return new ProjectException(cause);
    }

    public static ProjectException _new(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        return new ProjectException(message, cause, enableSuppression, writableStackTrace);
    }
}