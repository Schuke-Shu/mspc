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
    /**
     * 记录一条日志
     * @param sysLog 系统日志
     */
    void record(SysLog sysLog);

    /**
     * 根据 id 删除日志
     * @param id id
     */
    void removeById(Long id);

    /**
     * 根据 id 列表批量删除日志
     * @param ids id 列表
     */
    void removeByIds(List<Long> ids);

    /**
     * 清空日志
     */
    void clear();
}