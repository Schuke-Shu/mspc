package cn.mabbit.test.controller;

import cn.mabbit.mspc.cache.CacheUtil;
import cn.mabbit.mspc.core.annotation.Web;
import cn.mabbit.mspc.core.exception.SystemException;
import cn.mabbit.mspc.core.util.SpringUtil;
import cn.mabbit.mspc.core.web.ServiceCode;
import cn.mabbit.mspc.data.PageUtil;
import cn.mabbit.test.pojo.TestPO;
import cn.mabbit.test.pojo.dto.TestDTO;
import cn.mabbit.test.pojo.vo.TestVO;
import cn.mabbit.test.service.TestService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <h2></h2>
 *
 * @Date 2023-11-09 10:14
 */
@Slf4j
@Tag(name = "测试")
@Web(path = "/test")
@Setter(onMethod_ = @Autowired(required = false))
public class TestController
{
    private TestService service;
    private CacheUtil cacheUtil;
    private SpringUtil springUtil;

    @GetMapping("/aop")
    public void aop()
    {
        System.out.println(this.getClass());
        System.out.println(springUtil.getProxy(this).getClass());
    }

    @GetMapping("/demo/{arg}")
    @Operation(summary = "测试方法", parameters = @Parameter(name = "arg"))
    public String test(@PathVariable Integer arg)
    {
        System.out.println(service);
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
        throw new SystemException(ServiceCode.ERR_UNKNOWN);
    }

    @GetMapping("/page")
    @Operation(parameters = @Parameter(name = "test", description = "测试"))
    public PageInfo<TestVO> page(TestDTO dto)
    {
        return PageUtil.pagination(dto, service::page);
    }

    @GetMapping("/cache/{arg}")
    public void cache(@PathVariable String arg)
    {
        service.cache(arg);
    }

    @GetMapping("/read/{key}")
    public TestPO read(@PathVariable Long key)
    {
        log.debug("controller read");
        return service.read(key);
    }

    @GetMapping("/redis")
    public void redis()
    {
        TestPO po = new TestPO();
        po.setTest("123456");
        cacheUtil.set("test:::::t", po);
    }

    public void t1() {}
}