package cn.mabbit.test.pojo;

import cn.mabbit.mspc.data.pojo.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-12 13:35
 */
@Getter
@Setter
@Accessors(chain = true)
public class TestPO extends BasePO<String>
{
    private String test;
}