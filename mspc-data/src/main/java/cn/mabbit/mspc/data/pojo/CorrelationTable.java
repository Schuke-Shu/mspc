package cn.mabbit.mspc.data.pojo;

import java.io.Serializable;

/**
 * <h2>关联表接口</h2>
 *
 * @param <L> 左表主键类型
 * @param <R> 右表主键类型
 * @Date 2023-12-07 9:57
 */
public interface CorrelationTable<L extends Serializable, R extends Serializable>
{
    /**
     * @return 左表主键值
     */
    L getLid();

    /**
     * @return 右表主键值
     */
    R getRid();
}