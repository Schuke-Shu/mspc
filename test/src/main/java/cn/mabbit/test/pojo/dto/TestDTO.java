package cn.mabbit.test.pojo.dto;

import cn.mabbit.mspc.data.pojo.BasePageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-29 16:22
 */
@Schema(title = "测试DTO")
@EqualsAndHashCode(callSuper = true)
public class TestDTO extends BasePageDTO
{
    @Schema(title = "测试")
    String test;
}