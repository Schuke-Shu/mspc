package cn.mabbit.mspc.data.pojo;

import cn.mabbit.mdk4j.core.lang.Bool;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.mabbit.mdk4j.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2>实体表基类</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-07 9:41
 */
public abstract class BaseEntity<K extends Serializable>
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