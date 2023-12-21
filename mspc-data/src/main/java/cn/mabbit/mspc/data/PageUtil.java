package cn.mabbit.mspc.data;

import cn.mabbit.mdk4j.core.lang.Assert;
import cn.mabbit.mdk4j.core.lang.function.Action;
import cn.mabbit.mspc.core.exception.ProjectException;
import cn.mabbit.mspc.data.pojo.PageDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <h2>分页工具</h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-28 16:36
 */
public abstract class PageUtil
{
    private static boolean DEFAULT_COUNT;

    /**
     * 导航页值修正
     *
     * @param navNum 导航页数
     * @return 若 {@code navNum} 为 {@code null}，返回 {@link PageInfo#DEFAULT_NAVIGATE_PAGES}，否则返回原值
     */
    public static int navNumCorrect(Integer navNum)
    {
        return navNum == null ? PageInfo.DEFAULT_NAVIGATE_PAGES : navNum;
    }

    /**
     * 快速分页
     *
     * @param dto     分页DTO
     * @param querier 查询方法
     * @param <T>     数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(PageDTO dto, Action querier)
    {
        return pagination(dto.getPageNum(), dto.getPageSize(), dto.getNavNum(), querier, DEFAULT_COUNT);
    }

    /**
     * 快速分页
     *
     * @param dto     分页DTO
     * @param querier 查询方法
     * @param count   是否进行count查询
     * @param <T>     数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            PageDTO dto, Action querier, boolean count
    )
    {
        return pagination(dto, querier, count, null, null);
    }

    /**
     * 快速分页
     *
     * @param dto          分页DTO
     * @param querier      查询方法
     * @param count        是否进行count查询
     * @param reasonable   分页合理化,null时用默认配置
     * @param pageSizeZero true且pageSize=0时返回全部结果，false时分页,null时用默认配置
     * @param <T>          数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            PageDTO dto, Action querier, boolean count, Boolean reasonable, Boolean pageSizeZero
    )
    {
        return pagination(
                dto.getPageNum(), dto.getPageSize(), dto.getNavNum(), querier, count, reasonable, pageSizeZero);
    }

    /**
     * 快速分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param navNum   导航页数
     * @param querier  查询方法
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(Integer pageNum, Integer pageSize, Integer navNum, Action querier)
    {
        return pagination(pageNum, pageSize, navNum, querier, DEFAULT_COUNT);
    }

    /**
     * 快速分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param navNum   导航页数
     * @param querier  查询方法
     * @param count    是否进行count查询
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            Integer pageNum, Integer pageSize, Integer navNum, Action querier, boolean count
    )
    {
        return pagination(pageNum, pageSize, navNum, querier, count, null, null);
    }

    /**
     * 快速分页
     *
     * @param pageNum      页码
     * @param pageSize     每页显示数量
     * @param navNum       导航页数
     * @param querier      查询方法
     * @param count        是否进行count查询
     * @param reasonable   分页合理化,null时用默认配置
     * @param pageSizeZero true且pageSize=0时返回全部结果，false时分页,null时用默认配置
     * @param <T>          数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            Integer pageNum, Integer pageSize, Integer navNum, Action querier, boolean count,
            Boolean reasonable, Boolean pageSizeZero
    )
    {
        Assert.state(
                pageNum != null && pageSize != null,
                () -> ProjectException._new("The pagination parameter must not be null")
        );

        Page<T> page = PageHelper.startPage(pageNum, pageSize, count, reasonable, pageSizeZero);
        querier.act();

        return new PageInfo<>(page, navNumCorrect(navNum));
    }

    public static void setDefaultCount(Boolean defaultCount)
    {
        if (defaultCount != null) DEFAULT_COUNT = defaultCount;
    }
}