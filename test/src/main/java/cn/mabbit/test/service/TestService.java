package cn.mabbit.test.service;

import cn.mabbit.test.pojo.TestPO;
import cn.mabbit.test.pojo.dto.TestDTO;
import cn.mabbit.test.pojo.vo.TestVO;

import java.util.List;

/**
 * <h2>测试接口</h2>
 *
 * @Date 2023-11-27 15:24
 */
public interface TestService
{
    int test(String arg);

    List<TestVO> page(TestDTO dto);

    TestPO cache(String arg);

    TestPO read(Long key);
}