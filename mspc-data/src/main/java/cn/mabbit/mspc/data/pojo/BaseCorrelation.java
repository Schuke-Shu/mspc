package cn.mabbit.mspc.data.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>关联表基类</h2>
 *
 * @Date 2023-12-07 10:31
 */
@ToString(callSuper = true)
@Getter(onMethod_ = @Override)
@Setter(onMethod_ = @Override)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCorrelation extends BasePO implements CorrelationTable
{
    @Schema(description = "左表主键")
    protected Long lid;

    @Schema(description = "右表主键")
    protected Long rid;
}