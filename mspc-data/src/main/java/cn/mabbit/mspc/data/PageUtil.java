package cn.mabbit.mspc.data;

import cn.jruyi.core.lang.Action;
import cn.jruyi.core.lang.Assert;
import cn.mabbit.mspc.data.pojo.PageDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <h2>分页工具</h2>
 *
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
     * @param dto      分页DTO
     * @param selector 查询方法
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(PageDTO dto, Action selector)
    {
        return pagination(dto.getPageNum(), dto.getPageSize(), dto.getNavNum(), selector, DEFAULT_COUNT);
    }

    /**
     * 快速分页
     *
     * @param dto      分页DTO
     * @param selector 查询方法
     * @param count    是否进行count查询
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            PageDTO dto, Action selector, boolean count
    )
    {
        return pagination(dto, selector, count, null, null);
    }

    /**
     * 快速分页
     *
     * @param dto          分页DTO
     * @param selector     查询方法
     * @param count        是否进行count查询
     * @param reasonable   分页合理化,null时用默认配置
     * @param pageSizeZero true且pageSize=0时返回全部结果，false时分页,null时用默认配置
     * @param <T>          数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            PageDTO dto, Action selector, boolean count, Boolean reasonable, Boolean pageSizeZero
    )
    {
        return pagination(
                dto.getPageNum(), dto.getPageSize(), dto.getNavNum(), selector, count, reasonable, pageSizeZero);
    }

    /**
     * 快速分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param navNum   导航页数
     * @param selector 查询方法
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(Integer pageNum, Integer pageSize, Integer navNum, Action selector)
    {
        return pagination(pageNum, pageSize, navNum, selector, DEFAULT_COUNT);
    }

    /**
     * 快速分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param navNum   导航页数
     * @param selector 查询方法
     * @param count    是否进行count查询
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            Integer pageNum, Integer pageSize, Integer navNum, Action selector, boolean count
    )
    {
        return pagination(pageNum, pageSize, navNum, selector, count, null, null);
    }

    /**
     * 快速分页
     *
     * @param pageNum      页码
     * @param pageSize     每页显示数量
     * @param navNum       导航页数
     * @param selector     查询方法
     * @param count        是否进行count查询
     * @param reasonable   分页合理化,null时用默认配置
     * @param pageSizeZero true且pageSize=0时返回全部结果，false时分页,null时用默认配置
     * @param <T>          数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            Integer pageNum, Integer pageSize, Integer navNum, Action selector, boolean count,
            Boolean reasonable, Boolean pageSizeZero
    )
    {
        Assert.state(pageNum != null && pageSize != null, () -> "The pagination parameter must not be null");

        Page<T> page = PageHelper.startPage(pageNum, pageSize, count, reasonable, pageSizeZero);
        selector.go();

        return new PageInfo<>(page, navNumCorrect(navNum));
    }

    public static void setDefaultCount(Boolean defaultCount)
    {
        if (defaultCount != null) DEFAULT_COUNT = defaultCount;
    }
}