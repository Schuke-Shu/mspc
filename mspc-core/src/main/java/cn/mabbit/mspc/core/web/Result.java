package cn.mabbit.mspc.core.web;

import cn.mabbit.mspc.core.exception.ServiceException;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Date 2023/10/09 18:15
 */
@SuppressWarnings("rawtypes")
@Schema(name = "result", title = "JSON数据", description = "restful风格JSON数据")
public record Result<D>
        (
                @Schema(title = "响应码", description = "业务状态码")
                int code,
                @Schema(title = "响应数据", description = "业务处理成功时返回的数据")
                D data,
                @Schema(title = "响应文本", description = "业务处理失败时返回的信息")
                String msg
        )
        implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 业务处理成功，无返回数据
     *
     * @return {@link Result}
     */
    public static Result ok()
    {
        return ok(null);
    }

    /**
     * 业务处理成功，有返回数据
     *
     * @param data 业务处理完成后获得的数据
     * @param <D>  {@link Result}
     * @return 业务处理成功
     */
    public static <D> Result<D> ok(D data)
    {
        return new Result<>(ServiceCode.OK.code(), data, null);
    }

    /**
     * 业务处理失败
     *
     * @param code 业务状态码
     * @param msg  业务处理失败时返回的信息
     * @return {@link Result}
     */
    public static Result fail(int code, String msg)
    {
        return new Result<>(code, null, msg);
    }

    /**
     * 业务处理失败
     *
     * @param code {@link ServiceCode}
     * @return {@link Result}
     */
    public static Result fail(ServiceCode code)
    {
        return fail(code.code(), code.msg());
    }

    /**
     * 业务处理失败
     *
     * @param e 业务异常
     * @return {@link Result}
     */
    public static <E extends ServiceException> Result fail(E e)
    {
        return fail(e.code());
    }
}