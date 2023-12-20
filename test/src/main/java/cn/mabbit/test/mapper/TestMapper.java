package cn.mabbit.test.mapper;

import cn.mabbit.mspc.data.mapper.BaseMapper;
import cn.mabbit.test.pojo.TestPO;
import cn.mabbit.test.pojo.dto.TestDTO;
import cn.mabbit.test.pojo.vo.TestVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-27 15:26
 */
public interface TestMapper extends BaseMapper<Long, TestPO>
{
    int test(@Param("arg") String arg, @Param("time") LocalDateTime time);

    List<TestVO> page(TestDTO dto);
}