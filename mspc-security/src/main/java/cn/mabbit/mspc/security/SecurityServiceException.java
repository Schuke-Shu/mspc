package cn.mabbit.mspc.security;

import cn.mabbit.mspc.core.exception.ServiceException;
import cn.mabbit.mspc.core.web.ServiceCode;

/**
 * <h2></h2>
 *
 * @Date 2023/12/21 16:30
 */
public class SecurityServiceException
        extends ServiceException
{
    protected SecurityServiceException(ServiceCode code)
    {
        super(code, null);
    }

    public static SecurityServiceException _new(ServiceCode code)
    {
        return new SecurityServiceException(code);
    }
}