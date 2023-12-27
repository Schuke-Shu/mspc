package cn.mabbit.mspc.data.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h2>PO 接口</h2>
 *
 * @param <K> 主键类型
 * @Date 2023-12-14 10:43
 */
public interface PO<K extends Serializable>
        extends Serializable
{
    /**
     * @return 主键
     */
    K getId();

    /**
     * 设置主键
     *
     * @param k   主键
     */
    void setId(K k);

    /**
     * @return 创建时间
     */
    LocalDateTime getCreateTime();

    /**
     * 设置创建时间
     *
     * @param time 创建时间
     */
    void setCreateTime(LocalDateTime time);
}