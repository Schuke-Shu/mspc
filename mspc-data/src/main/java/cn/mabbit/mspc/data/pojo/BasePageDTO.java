package cn.mabbit.mspc.data.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * <h2>分页查询DTO</h2>
 *
 * @Date 2023-11-08 18:04
 */
@ToString
@EqualsAndHashCode
@Setter(onMethod_ = @Override)
public class BasePageDTO implements PageDTO
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "当前页")
    @Getter(onMethod_ = {@Min(1), @Override})
    protected Integer pageNum;

    @Schema(description = "每页元素数")
    @Getter(onMethod_ = {@Min(0), @Override})
    protected Integer pageSize;

    @Getter(onMethod_ = @Override)
    @Schema(description = "导航页数")
    protected Integer navNum;
}