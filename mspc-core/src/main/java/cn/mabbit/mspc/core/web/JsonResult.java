package cn.mabbit.mspc.core.web;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Date 2023/10/09 18:15
 */
@Schema(name = "result", title = "JSON数据", description = "restful风格JSON数据")
public record JsonResult<D>
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
}