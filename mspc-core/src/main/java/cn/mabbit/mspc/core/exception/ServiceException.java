package cn.mabbit.mspc.core.exception;

import cn.mabbit.mspc.core.consts.ServiceCodePool;
import cn.mabbit.mspc.core.web.ServiceCode;
import lombok.Getter;

import java.io.Serial;

/**
 * <h2>全局业务异常</h2>
 *
 * @author 一只枫兔
 * @Date 2023/10/09 18:15
 */
@Getter
public class ServiceException
        extends BaseException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public static final ServiceException COMMON = _new();

    private final ServiceCode serviceCode;

    protected ServiceException(ServiceCode code, String detail)
    {
        super(code.msg());
        this.serviceCode = code;
        this.detail = detail;
    }

    public static ServiceException _new()
    {
        return _new(ServiceCodePool.ERR_UNKNOWN);
    }

    public static ServiceException _new(String detail)
    {
        return _new(ServiceCodePool.ERR_UNKNOWN, detail);
    }

    public static ServiceException _new(ServiceCode code)
    {
        return _new(code, null);
    }

    public static ServiceException _new(ServiceCode code, String detail)
    {
        return new ServiceException(code, detail);
    }
}