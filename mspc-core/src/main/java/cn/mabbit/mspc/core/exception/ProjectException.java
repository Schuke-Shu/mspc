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

    public ProjectException() {}

    public ProjectException(String message)
    {
        super(message);
    }

    public ProjectException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ProjectException(Throwable cause)
    {
        super(cause);
    }

    public ProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}