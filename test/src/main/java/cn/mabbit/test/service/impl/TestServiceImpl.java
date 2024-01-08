package cn.mabbit.test.service.impl;

import cn.mabbit.mspc.core.BaseRequestContextHandler;
import cn.mabbit.test.pojo.TestPO;
import cn.mabbit.test.pojo.dto.TestDTO;
import cn.mabbit.test.pojo.vo.TestVO;
import cn.mabbit.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h2></h2>
 *
 * @Date 2023-11-27 15:26
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService
{
    @Override
    public int test(String arg)
    {
        return 0;
    }

    @Override
    public List<TestVO> page(TestDTO dto)
    {
        return null;
    }

    @Override
    @CachePut(value = "cache", key = "'key:' + @requestContext.get('" + BaseRequestContextHandler.REQUEST_TIME + "')")
    public TestPO cache(String arg)
    {
        TestPO po = new TestPO();
        po.setId(1L);
        po.setTest(arg);
        po.setCreateTime(BaseRequestContextHandler.requestTime());
        return po;
    }

    @Override
    @Cacheable(value = "cache", key = "'key:' + #key")
    public TestPO read(Long key)
    {
        log.debug("read");
        return new  TestPO();
    }
}