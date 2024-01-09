package cn.mabbit.mspc.data.pojo;

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
 * <h2>持久化对象基类</h2>
 *
 * <p>Persistant Object 持久化对象，与数据库表对应</p>
 *
 * @Date 2023-09-04 13:18
 */
@ToString
@Getter(onMethod_ = @Override)
@Setter(onMethod_ = @Override)
@EqualsAndHashCode
public abstract class BasePO implements PO
{
    @Schema(description = "id")
    protected Long id;

    @EqualsAndHashCode.Exclude
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DATETIME_PATTERN)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createTime;
}