package cn.mabbit.mspc.data.pojo;

import cn.mabbit.mdk4j.core.lang.Bool;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.mabbit.mdk4j.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2>实体表基类</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-07 9:41
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEntity<K extends Serializable>
        extends BasePO<K>
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
}