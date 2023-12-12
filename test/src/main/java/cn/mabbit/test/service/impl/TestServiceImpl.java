package cn.mabbit.test.service.impl;

import cn.mabbit.test.mapper.TestMapper;
import cn.mabbit.test.pojo.dto.TestDTO;
import cn.mabbit.test.pojo.vo.TestVO;
import cn.mabbit.test.service.TestService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-27 15:26
 */
@Service
@Setter(onMethod_ = @Autowired)
public class TestServiceImpl implements TestService
{
    private TestMapper mapper;

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
}