package cn.mabbit.mspc.data.pojo;

import java.io.Serializable;

/**
 * <h2>分页 DTO 接口</h2>
 *
 * @Date 2023-12-14 11:07
 */
public interface PageDTO extends Serializable
{
    /**
     * @return 当前页
     */
    Integer getPageNum();

    /**
     * @return 每页元素数
     */
    Integer getPageSize();

    /**
     * @return 导航页数
     */
    Integer getNavNum();

    /**
     * 设置当前页
     *
     * @param num 当前页
     */
    void setPageNum(Integer num);

    /**
     * 设置每页元素数
     *
     * @param size 每页元素数
     */
    void setPageSize(Integer size);

    /**
     * 设置导航页数
     *
     * @param num 导航页数
     */
    void setNavNum(Integer num);
}