package cn.mabbit.mspc.log;

import java.util.List;

/**
 * <h2>日志服务接口</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-13 12:45
 */
public interface LogService
{
    void record(SysLog log);

    void removeById(Long id);

    void removeByIds(List<Long> ids);

    void clear();
}