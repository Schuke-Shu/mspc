package cn.mabbit.mspc.data.pojo;

import cn.jruyi.core.lang.Bool;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h2>实体表接口</h2>
 *
 * @Date 2023-12-14 10:52
 */
public interface Entity<K extends Serializable>
        extends PO<K>
{
    /**
     * @return 状态
     */
    Integer getStatus();

    /**
     * 设置状态
     *
     * @param status 状态参数
     */
    void setStatus(Integer status);

    /**
     * @return 备注
     */
    String getRemark();

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    void setRemark(String remark);

    /**
     * @return 是否被删除
     */
    Bool getDeleted();

    /**
     * 设置是否被删除
     *
     * @param deleted 删除标记参数
     */
    void setDeleted(Bool deleted);

    /**
     * @return 最后修改时间
     */
    LocalDateTime getModifiedTime();

    /**
     * 设置最后修改时间
     *
     * @param modifiedTime 最后修改时间
     */
    void setModifiedTime(LocalDateTime modifiedTime);
}