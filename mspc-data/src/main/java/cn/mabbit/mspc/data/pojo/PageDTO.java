package cn.mabbit.mspc.data.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h2>分页查询DTO</h2>
 *
 * @author 一只枫兔
 * @Date 2023/11/8 18:04
 */
@Getter
@Setter
@Accessors(chain = true)
public class PageDTO
        implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Min(0)
    @NotNull
    @Schema(description = "当前页数", requiredMode = Schema.RequiredMode.REQUIRED)
    protected Integer pageNum;

    @Min(1)
    @NotNull
    @Schema(description = "每页元素数", requiredMode = Schema.RequiredMode.REQUIRED)
    protected Integer pageSize;

    @Schema(description = "导航页数")
    protected Integer navNum;
}