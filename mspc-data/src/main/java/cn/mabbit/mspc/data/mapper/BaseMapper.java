package cn.mabbit.mspc.data.mapper;

import cn.mabbit.mspc.data.pojo.BasePO;

import java.io.Serializable;
import java.util.List;

/**
 * <h2>mapper基础模板</h2>
 *
 * @param <K> 主键类型
 * @param <E> 实体类型
 * @author 一只枫兔
 * @Date 2023-11-29 9:46
 */
@SuppressWarnings("unchecked")
public interface BaseMapper<K extends Serializable, E extends BasePO<K>>
{
    /**
     * 新增一条数据
     *
     * @param e 实体数据
     * @return 新增成功数量
     */
    int save(E e);

    /**
     * 批量插入数据
     *
     * @param es 实体数据数组
     * @return 新增成功数量
     */
    int saveBatch(E... es);

    /**
     * 根据主键删除一条数据
     *
     * @param k 主键
     * @return 删除成功数量
     */
    int removeById(K k);

    /**
     * 根据主键数组批量删除数据
     *
     * @param ks 主键数组
     * @return 删除成功数量
     */
    int removeBatch(K... ks);

    /**
     * 编辑一条数据
     *
     * @param e 实体数据
     * @return 编辑成功数量
     */
    int edit(E e);

    /**
     * 根据主键查询数据
     *
     * @param k 主键
     * @return 实体数据
     */
    E queryById(K k);

    /**
     * 条件查询
     *
     * @param e 实体数据
     * @return 实体数据
     */
    List<E> queryBy(E e);

    /**
     * 列出所有数据
     *
     * @return 实体数据列表
     */
    List<E> list();

    /**
     * 根据主键列表列出数据
     *
     * @param ks 主键列表
     * @return 实体数据列表
     */
    List<E> listByIds(K... ks);

    /**
     * @return 表中数据总量
     */
    int count();

    /**
     * 根据主键统计表中数据量
     *
     * @param k 主键
     * @return 数据量
     */
    int countById(K k);
}