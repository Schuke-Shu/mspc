package cn.mabbit.mspc.core.exception;

import cn.mabbit.mspc.core.web.ServiceCode;

import java.io.Serial;

/**
 * <h2>系统异常</h2>
 *
 * @Date 2023-12-21 19:43
 */
public class SystemException extends BaseException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public SystemException(ServiceCode code)
    {
        super(code);
    }

    public SystemException(ServiceCode code, String detail)
    {
        super(code, detail);
    }
}