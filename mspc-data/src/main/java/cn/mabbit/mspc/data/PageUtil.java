package cn.mabbit.mspc.data;

import cn.jruyi.core.lang.Action;
import cn.mabbit.mspc.data.pojo.PageDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

import static cn.jruyi.core.util.ObjectUtil.DON;

/**
 * <h2>分页工具</h2>
 *
 * @Date 2023-11-28 16:36
 */
@Slf4j
public abstract class PageUtil extends PageHelper
{
    /**
     * 快速分页
     *
     * @param dto      分页DTO
     * @param selector 查询方法
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T, PD extends PageDTO> PageInfo<T> pagination(PD dto, Consumer<PD> selector)
    {
        return pagination(dto, selector, (Consumer<Page<T>>) null);
    }

    /**
     * 快速分页
     *
     * @param dto      分页DTO
     * @param selector 查询方法
     * @param orderBy  排序字段
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T, PD extends PageDTO> PageInfo<T> pagination(PD dto, Consumer<PD> selector, String orderBy)
    {
        return pagination(dto, selector, page -> page.setOrderBy(orderBy));
    }

    /**
     * 快速分页
     *
     * @param dto        分页DTO
     * @param selector   查询方法
     * @param pageSetter 分页配置
     * @param <T>        数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T, PD extends PageDTO> PageInfo<T> pagination(
            PD dto, Consumer<PD> selector, Consumer<Page<T>> pageSetter
    )
    {
        Page<T> page = start(dto.getPageNum(), dto.getPageSize(), pageSetter);
        selector.accept(dto);
        return page(page, dto.getNavNum());
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
        return pagination(pageNum, pageSize, navNum, selector, (Consumer<Page<T>>) null);
    }

    /**
     * 快速分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param navNum   导航页数
     * @param selector 查询方法
     * @param orderBy  排序字段
     * @param <T>      数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            Integer pageNum, Integer pageSize, Integer navNum, Action selector, String orderBy
    )
    {
        return pagination(pageNum, pageSize, navNum, selector, page -> page.setOrderBy(orderBy));
    }

    /**
     * 快速分页
     *
     * @param pageNum    页码
     * @param pageSize   每页显示数量
     * @param navNum     导航页数
     * @param selector   查询方法
     * @param pageSetter 分页配置
     * @param <T>        数据列表元素类型
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagination(
            Integer pageNum, Integer pageSize, Integer navNum, Action selector, Consumer<Page<T>> pageSetter
    )
    {
        Page<T> page = start(pageNum, pageSize, pageSetter);
        selector.go();
        return page(page, navNum);
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     */
    public static <E> Page<E> start(Integer pageNum, Integer pageSize)
    {
        return start(pageNum, pageSize, (Consumer<Page<E>>) null);
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param orderBy  排序字段
     */
    public static <E> Page<E> start(Integer pageNum, Integer pageSize, String orderBy)
    {
        return start(pageNum, pageSize, page -> page.setOrderBy(orderBy));
    }

    /**
     * 开始分页
     *
     * @param pageNum    页码
     * @param pageSize   每页显示数量
     * @param pageSetter 分页配置
     */
    public static <E> Page<E> start(Integer pageNum, Integer pageSize, Consumer<Page<E>> pageSetter)
    {
        Page<E> page = startPage(
                DON(pageNum, 1),
                DON(pageSize, 0)
        );
        if (pageSetter != null) pageSetter.accept(page);

        log.debug("开始分页，页码【{}】，每页数据量【{}】", pageNum, pageSize);
        return page;
    }

    /**
     * @param list 数据列表
     * @param <T>  数据列表元素类型
     * @return 分页数据
     */
    public static <T> PageInfo<T> page(List<T> list)
    {
        return page(list, null);
    }

    /**
     * @param list   数据列表
     * @param navNum 导航页数
     * @param <T>    数据列表元素类型
     * @return 分页数据
     */
    public static <T> PageInfo<T> page(List<T> list, Integer navNum)
    {
        log.debug("获取分页数据，导航页数【{}】", navNum);
        return navNum == null ?
                new PageInfo<>(list) :
                new PageInfo<>(
                        list,
                        DON(navNum, PageInfo.DEFAULT_NAVIGATE_PAGES)
                );
    }
}