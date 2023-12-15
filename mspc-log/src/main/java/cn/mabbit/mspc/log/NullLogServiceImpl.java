package cn.mabbit.mspc.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h2>日志服务接口实现类（空）</h2>
 *
 * <p>创建项目时必须自行添加日志实现类，调用该实现类的方法会抛出 {@link NullPointerException}</p>
 *
 * @author 一只枫兔
 * @Date 2023-12-13 12:45
 */
@Slf4j
@Service
@ConditionalOnMissingBean(LogService.class)
public class NullLogServiceImpl
        implements LogService
{
    public NullLogServiceImpl()
    {
        log.warn("No [LogServiceImpl] has been find, the logging feature will not take effect");
    }

    @Override
    public void record(SysLog sysLog)
    {
        throw new NullPointerException();
    }

    @Override
    public void remove(Long id)
    {
        throw new NullPointerException();
    }

    @Override
    public void removeBatch(List<Long> ids)
    {
        throw new NullPointerException();
    }

    @Override
    public void clear()
    {
        throw new NullPointerException();
    }

    @Override
    public void export()
    {
        throw new NullPointerException();
    }
}