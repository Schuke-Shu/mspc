package cn.mabbit.mspc.core.exception;

import cn.mabbit.mspc.core.web.ServiceCode;
import lombok.Getter;

import java.io.Serial;

/**
 * <h2>业务异常</h2>
 *
 * @Date 2023-10-09 18:15
 */
@Getter
public class ServiceException
        extends BaseException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public ServiceException(ServiceCode code)
    {
        super(code);
    }

    public ServiceException(ServiceCode code, String detail)
    {
        super(code, detail);
    }
}