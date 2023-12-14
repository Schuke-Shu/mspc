package cn.mabbit.mspc.data.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;

/**
 * <h2>分页查询DTO</h2>
 *
 * @author 一只枫兔
 * @Date 2023/11/8 18:04
 */
public class BasePageDTO
        implements PageDTO
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "当前页数", requiredMode = Schema.RequiredMode.REQUIRED)
    protected Integer pageNum;
    @Schema(description = "每页元素数", requiredMode = Schema.RequiredMode.REQUIRED)
    protected Integer pageSize;
    @Schema(description = "导航页数")
    protected Integer navNum;

    @Min(1)
    @NotNull
    @Override
    public Integer getPageNum()
    {
        return pageNum;
    }

    @Override
    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    @Min(1)
    @NotNull
    @Override
    public Integer getPageSize()
    {
        return pageSize;
    }

    @Override
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    @Override
    public Integer getNavNum()
    {
        return navNum;
    }

    @Override
    public void setNavNum(Integer navNum)
    {
        this.navNum = navNum;
    }
}