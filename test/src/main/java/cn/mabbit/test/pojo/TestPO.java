package cn.mabbit.test.pojo;

import cn.mabbit.mspc.data.pojo.BasePO;
import lombok.*;

/**
 * <h2></h2>
 *
 * @Date 2023-12-12 13:35
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TestPO extends BasePO<Long>
{
    private String test;
}