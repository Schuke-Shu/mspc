package cn.mabbit.mspc.data.pojo;

import java.io.Serializable;

/**
 * <h2>分页 DTO 接口</h2>
 *
 * @Date 2023-12-14 11:07
 */
public interface PageDTO
        extends Serializable
{
    Integer getPageNum();

    void setPageNum(Integer num);

    Integer getPageSize();

    void setPageSize(Integer size);

    Integer getNavNum();

    void setNavNum(Integer num);
}