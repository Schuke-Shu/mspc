package cn.mabbit.mspc.log;

import lombok.Setter;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-13 12:44
 */
@Aspect
@Component
@Setter(onMethod_ = @Autowired)
public class LogAspect
{
    private LogService logService;
}