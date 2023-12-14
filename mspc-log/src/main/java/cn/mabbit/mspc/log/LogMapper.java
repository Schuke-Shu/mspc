package cn.mabbit.mspc.log;

import cn.mabbit.mspc.data.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-14 15:39
 */
@Mapper
@ConditionalOnMissingBean(LogService.class)
public interface LogMapper
        extends BaseMapper<Long, SysLog>
{
    /**
     * 清空数据
     */
    void clear();
}