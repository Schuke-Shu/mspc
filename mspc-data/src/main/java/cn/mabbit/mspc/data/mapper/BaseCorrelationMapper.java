package cn.mabbit.mspc.data.mapper;

import cn.mabbit.mspc.data.pojo.BaseCorrelation;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <h2>关联表mapper基础模板</h2>
 *
 * @param <T> 关联表实体
 * @param <L> 左表主键类型
 * @param <R> 右表主键类型
 * @author 一只枫兔
 * @Date 2023-12-07 10:43
 */
public interface BaseCorrelationMapper<T extends BaseCorrelation<L, R>, L extends Serializable, R extends Serializable>
        extends BaseMapper<Long, T>
{
    /**
     * 根据左表主键批量删除
     *
     * @param lid 左表主键
     * @return 删除成功数量
     */
    int removeByLid(Long lid);

    /**
     * 根据右表主键批量删除
     *
     * @param rid 右表主键
     * @return 删除成功数量
     */
    int removeByRid(Long rid);

    /**
     * 根据左表主键列出关联表数据
     *
     * @param id 左表主键
     * @return 实体数据列表
     */
    List<T> listByLid(L id);

    /**
     * 根据右表主键列出关联表数据
     *
     * @param id 右表主键
     * @return 实体数据列表
     */
    List<T> listByRid(R id);

    /**
     * 根据双方主键查询数据
     *
     * @param lid 左表主键
     * @param rid 右表主键
     * @return 实体数据
     */
    T queryByBoth(@Param("lid") L lid, @Param("rid") R rid);
}