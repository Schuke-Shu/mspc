package cn.mabbit.mspc.data.pojo;

import cn.mabbit.mspc.core.Bool;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static cn.jruyi.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2>实体表基类</h2>
 *
 * @Date 2023-12-07 9:41
 */
@ToString(callSuper = true)
@Getter(onMethod_ = @Override)
@Setter(onMethod_ = @Override)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends BasePO implements Entity
{
    @Schema(description = "状态")
    protected Integer status;

    @Schema(description = "备注")
    protected String remark;

    @Schema(description = "是否被删除")
    protected Bool deleted;

    @EqualsAndHashCode.Exclude
    @Schema(description = "最后修改时间")
    @JsonFormat(pattern = DATETIME_PATTERN)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime modifiedTime;
}