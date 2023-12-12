package cn.mabbit.mspc.data.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.mabbit.mdk4j.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2>持久化对象基类</h2>
 *
 * <p>Persistant Object 持久化对象，与数据库表一一对应</p>
 *
 * @param <K> 主键类型
 * @author 一只枫兔
 * @Date 2023/9/4 13:18
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class BasePO<K extends Serializable>
        implements Serializable
{
    /**
     * id
     */
    protected K id;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DATETIME_PATTERN)
    protected LocalDateTime createTime;
}