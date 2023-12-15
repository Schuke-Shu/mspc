package cn.mabbit.mspc.core;

import cn.mabbit.mspc.core.exception.ProjectException;
import cn.mabbit.mspc.core.util.ServletUtil;
import cn.mabbit.mspc.core.web.R;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import static cn.mabbit.mspc.core.consts.KeyConsts.REQUEST_TIME;

/**
 * <h2>Api 接口 AOP</h2>
 *
 * <p>
 *     请求到达时向 {@link SyncContext} 添加到达时间，
 *     请求结束时将请求结果包装为 {@link cn.mabbit.mspc.core.web.JsonResult JsonResult} 并返回，
 *     然后清除 {@link SyncContext}
 * </p>
 *
 * @author 一只枫兔
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
            SyncContext.put(REQUEST_TIME, LocalDateTime.now());
            // 放行
            Object result = point.proceed();
            // 包装结果并返回
            responseJson(R.ok(result));
            return null;
        }
        finally
        {
            // 清除 ThreadLocal
            SyncContext.clean();
        }
    }

    private static void responseJson(Object data)
    {
        HttpServletResponse res = ServletUtil.getResponse();

        res.setContentType("application/json;charset=utf-8");

        try (PrintWriter writer = res.getWriter())
        {
            writer.write(
                    JSON.toJSONString(data)
            );
            writer.flush();
        }
        catch (IOException e)
        {
            log.error("Failed to send response data, msg: {}", e.getMessage());
            throw new ProjectException("-- Failed to send response data", e);
        }
    }
}