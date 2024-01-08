package cn.mabbit.mspc.core;

import cn.jruyi.core.lang.constant.TimeConsts;
import cn.mabbit.mspc.core.util.IpUtil;
import cn.mabbit.mspc.core.util.ServletUtil;
import cn.mabbit.mspc.core.web.Result;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h2>Api 接口 AOP</h2>
 *
 * <p>
 * 请求到达时向 {@link RequestContext} 添加到达时间，
 * 请求结束时将请求结果包装为 {@link Result Result} 并返回，
 * 然后清除 {@link RequestContext}
 * </p>
 *
 * @Date 2023-11-27 16:47
 */
@Slf4j
@Aspect
@Component
@Setter(onMethod_ = @Autowired)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiAspect
{
    private RequestContext context;

    @Around("cn.mabbit.mspc.core.consts.GlobalPointCuts.api()")
    public Object returning(ProceedingJoinPoint point) throws Throwable
    {
        try (RequestContext ignored = context) // 利用 Closeable 接口自动清除请求上下文
        {
            // 添加请求到达时间
            var now = LocalDateTime.now();
            BaseRequestContextHandler.requestTime(now);

            if (log.isDebugEnabled())
                log.debug(
                        """
                                                                
                                接收到【{}】请求：
                                时间：{}
                                IP：{}
                                """,
                        ServletUtil.getMethod(),
                        DateTimeFormatter.ofPattern(TimeConsts.DATETIME_PATTERN).format(now),
                        IpUtil.getIp()
                );

            // 放行，获取结果
            Result<?> result = Result.ok(point.proceed());
            log.debug("\n请求结果：\n{}", result);

            // 包装结果并返回
            ServletUtil.responseJson(result);
            return null;
        }
    }
}