package cn.mabbit.test.service.impl;

import cn.mabbit.mspc.core.ThreadContext;
import cn.mabbit.test.pojo.TestPO;
import cn.mabbit.test.pojo.dto.TestDTO;
import cn.mabbit.test.pojo.vo.TestVO;
import cn.mabbit.test.service.TestService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static cn.mabbit.mspc.core.consts.KeyConsts.REQUEST_TIME;

/**
 * <h2></h2>
 *
 * @Date 2023-11-27 15:26
 */
@Slf4j
@Service
@Setter(onMethod_ = @Autowired)
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
    @CachePut(value = "cache", key = "'key:' + #result.id")
    public TestPO cache(String arg)
    {
        TestPO po = new TestPO();
        po.setId(1L);
        po.setTest(arg);
        po.setCreateTime((LocalDateTime) ThreadContext.get(REQUEST_TIME));
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