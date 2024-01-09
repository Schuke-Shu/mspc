package cn.mabbit.mspc.data.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h2>PO 接口</h2>
 *
 * @Date 2023-12-14 10:43
 */
public interface PO extends Serializable
{
    /**
     * @return 主键
     */
    Long getId();

    /**
     * @return 创建时间
     */
    LocalDateTime getCreateTime();

    /**
     * 设置主键
     *
     * @param id 主键
     */
    void setId(Long id);

    /**
     * 设置创建时间
     *
     * @param time 创建时间
     */
    void setCreateTime(LocalDateTime time);
}