package cn.mabbit.mspc.data.pojo;

import cn.jruyi.core.lang.Bool;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.jruyi.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2>实体表基类</h2>
 *
 * @Date 2023-12-07 9:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity<K extends Serializable> extends BasePO<K>
        implements Entity<K>
{
    /**
     * 状态
     */
    protected Integer status;
    /**
     * 备注
     */
    protected String remark;
    /**
     * 是否被删除
     */
    protected Bool deleted;
    /**
     * 最后修改时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = DATETIME_PATTERN)
    protected LocalDateTime modifiedTime;

    @Override
    public Integer getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Override
    public String getRemark()
    {
        return remark;
    }

    @Override
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public Bool getDeleted()
    {
        return deleted;
    }

    @Override
    public void setDeleted(Bool deleted)
    {
        this.deleted = deleted;
    }

    @Override
    public LocalDateTime getModifiedTime()
    {
        return modifiedTime;
    }

    @Override
    public void setModifiedTime(LocalDateTime modifiedTime)
    {
        this.modifiedTime = modifiedTime;
    }
}