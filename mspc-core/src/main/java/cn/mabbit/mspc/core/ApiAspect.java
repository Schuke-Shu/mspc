package cn.mabbit.mspc.core;

import cn.mabbit.mspc.core.util.IpUtil;
import cn.mabbit.mspc.core.util.ServletUtil;
import cn.mabbit.mspc.core.web.JsonResult;
import cn.mabbit.mspc.core.web.R;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static cn.mabbit.mspc.core.consts.KeyConsts.REQUEST_TIME;

/**
 * <h2>Api 接口 AOP</h2>
 *
 * <p>
 * 请求到达时向 {@link RequestContext} 添加到达时间，
 * 请求结束时将请求结果包装为 {@link cn.mabbit.mspc.core.web.JsonResult JsonResult} 并返回，
 * 然后清除 {@link RequestContext}
 * </p>
 *
 * @Date 2023-11-27 16:47
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class ApiAspect
{
    @Around("cn.mabbit.mspc.core.consts.GlobalPointCuts.api()")
    public Object returning(ProceedingJoinPoint point)
            throws Throwable
    {
        try
        {
            // 添加请求到达时间
            var now = LocalDateTime.now();
            RequestContext.put(REQUEST_TIME, now);

            if (log.isDebugEnabled())
            {
                log.debug(
                        """
                                                                
                                接收到【{}】请求：
                                时间：{}
                                IP：{}
                                """,
                        ServletUtil.getMethod(),
                        now,
                        IpUtil.getIp()
                );
            }

            // 放行
            var r = point.proceed();
            JsonResult<Object> result = R.ok(r);
            log.debug("请求结果：\n{}", result);

            // 包装结果并返回
            ServletUtil.responseJson(result);
            return null;
        }
        finally
        {
            // 清除 ThreadLocal
            log.debug("清空线程上下文");
            RequestContext.clean();
        }
    }
}