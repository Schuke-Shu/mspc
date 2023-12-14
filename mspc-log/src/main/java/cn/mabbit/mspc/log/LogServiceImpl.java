package cn.mabbit.mspc.log;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h2>默认日志服务接口实现类</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-13 12:45
 */
@Slf4j
@Service
@Setter(onMethod_ = @Autowired)
@ConditionalOnMissingBean(LogService.class)
public class LogServiceImpl
        implements LogService
{
    private LogMapper logMapper;

    @Override
    public void record(SysLog sysLog)
    {
        if (logMapper.save(sysLog) < 1)
            log.warn("System logging failed");
    }

    @Override
    public void removeById(Long id)
    {
        logMapper.removeById(id);
    }

    @Override
    public void removeByIds(List<Long> ids)
    {
        logMapper.removeBatch(ids);
    }

    @Override
    public void clear()
    {
        logMapper.clear();
    }
}