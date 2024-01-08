package cn.mabbit.test.service.impl;

import cn.mabbit.mspc.log.LogService;
import cn.mabbit.mspc.log.SysLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h2>日志服务测试</h2>
 *
 * @Date 2024/1/4 13:53
 */
@Service
public class TestLogService implements LogService
{
    @Override
    public void record(SysLog sysLog)
    {

    }

    @Override
    public void remove(Long id)
    {

    }

    @Override
    public void removeBatch(List<Long> ids)
    {

    }

    @Override
    public void clear()
    {

    }

    @Override
    public void export()
    {

    }
}