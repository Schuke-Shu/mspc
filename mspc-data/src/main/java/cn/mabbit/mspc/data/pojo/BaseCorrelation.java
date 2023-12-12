package cn.mabbit.mspc.data.pojo;

import java.io.Serializable;

/**
 * <h2>关联表基类</h2>
 *
 * @param <L> 左表主键类型
 * @param <R> 右表主键类型
 * @author 一只枫兔
 * @Date 2023-12-07 10:31
 */
public abstract class BaseCorrelation<L extends Serializable, R extends Serializable>
        extends BasePO<Long>
        implements CorrelationTable<L, R>
{}