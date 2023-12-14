package cn.mabbit.mspc.core.web;

import cn.mabbit.mspc.core.consts.ServiceCodePool;
import cn.mabbit.mspc.core.exception.ServiceException;

/**
 * <h2>包装业务返回信息接口</h2>
 *
 * @author 一只枫兔
 * @Date 2023/9/2 16:43
 */
public interface R
{
    /**
     * 业务处理成功，无返回数据
     *
     * @return {@link JsonResult}
     */
    static JsonResult<Object> ok()
    {
        return ok(null);
    }

    /**
     * 业务处理成功，有返回数据
     *
     * @param data 业务处理完成后获得的数据
     * @param <D>  {@link JsonResult}
     * @return 业务处理成功
     */
    static <D> JsonResult<D> ok(D data)
    {
        return new JsonResult<>(ServiceCodePool.OK.code(), data, null);
    }

    /**
     * 业务处理失败
     *
     * @param code 业务状态码
     * @param msg  业务处理失败时返回的信息
     * @return {@link JsonResult}
     */
    static JsonResult<Object> fail(int code, String msg)
    {
        return new JsonResult<>(code, null, msg);
    }

    /**
     * 业务处理失败
     *
     * @param code {@link ServiceCode}
     * @return {@link JsonResult}
     */
    static JsonResult<Object> fail(ServiceCode code)
    {
        return fail(code.code(), code.msg());
    }

    /**
     * 业务处理失败
     *
     * @param e 业务异常
     * @return {@link JsonResult}
     */
    static <E extends ServiceException> JsonResult<Object> fail(E e)
    {
        return fail(e.getServiceCode());
    }
}