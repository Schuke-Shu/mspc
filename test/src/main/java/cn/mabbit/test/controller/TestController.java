package cn.mabbit.test.controller;

import cn.mabbit.mspc.core.exception.ProjectException;
import cn.mabbit.mspc.core.web.Api;
import cn.mabbit.mspc.data.PageUtil;
import cn.mabbit.test.pojo.dto.TestDTO;
import cn.mabbit.test.pojo.vo.TestVO;
import cn.mabbit.test.service.TestService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023/11/9 10:14
 */
@Validated
@Api(path = "/test")
@Tag(name = "测试")
@Setter(onMethod_ = @Autowired)
public class TestController
{
    private TestService service;

    @GetMapping("/demo/{arg}")
    @Operation(summary = "测试方法", parameters = @Parameter(name = "arg"))
    public String test(@PathVariable Integer arg)
    {
        return "Number: " + arg;
    }

    @GetMapping("/sql/{arg}")
    public void sqlTest(@PathVariable @Length(max = 1) String arg)
    {
        service.test(arg);
    }

    @GetMapping("/void")
    public void test()
    {
        throw new ProjectException("test");
    }

    @GetMapping("/page")
    @Operation(parameters = @Parameter(name = "test", description = "测试"))
    public PageInfo<TestVO> page(TestDTO dto)
    {
        return PageUtil.pagination(dto, () -> service.page(dto));
    }
}