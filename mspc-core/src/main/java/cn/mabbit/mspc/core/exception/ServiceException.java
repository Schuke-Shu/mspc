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

    public static final ServiceException COMMON = new ServiceException();

    private final ServiceCode serviceCode;

    public ServiceException()
    {
        this(ServiceCodePool.ERR_UNKNOWN);
    }

    public ServiceException(String detail)
    {
        this(ServiceCodePool.ERR_UNKNOWN);
        this.detail = detail;
    }

    public ServiceException(ServiceCode code)
    {
        super(code.msg());
        this.serviceCode = code;
    }

    public ServiceException(ServiceCode code, String detail)
    {
        super(code.msg());
        this.serviceCode = code;
        this.detail = detail;
    }
}