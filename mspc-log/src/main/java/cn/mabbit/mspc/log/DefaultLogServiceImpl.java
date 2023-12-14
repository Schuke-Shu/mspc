package cn.mabbit.mspc.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

/**
 * <h2>默认日志服务接口实现类</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-13 12:45
 */
@ConditionalOnMissingBean(LogService.class)
@Service("logService")
public class DefaultLogServiceImpl
{
}