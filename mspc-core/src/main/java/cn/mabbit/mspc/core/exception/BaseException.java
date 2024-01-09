package cn.mabbit.mspc.core.exception;

import cn.mabbit.mspc.core.web.ServiceCode;

/**
 * <h2>异常基类</h2>
 *
 * @Date 2023-10-09 18:15
 */
public abstract class BaseException
        extends RuntimeException
{
    /**
     * 细节信息，用于内部调试
     */
    protected final String detail;

    /**
     * 状态码
     */
    protected final ServiceCode code;

    public BaseException(ServiceCode code)
    {
        this(code, null);
    }

    public BaseException(ServiceCode code, String detail)
    {
        this.code = code;
        this.detail = detail;
    }

    public String detail()
    {
        return detail;
    }

    public ServiceCode code()
    {
        return code;
    }
}