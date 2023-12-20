package cn.mabbit.test.pojo;

import cn.mabbit.mspc.data.pojo.BasePO;
import lombok.*;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-12 13:35
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "_new")
@AllArgsConstructor(staticName = "_new")
public class TestPO extends BasePO<Long>
{
    private String test;
}