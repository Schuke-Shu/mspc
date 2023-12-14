package cn.mabbit.mspc.core.aop;

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

/**
 * <h2>Controller 层统一返回值 AOP</h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-27 16:47
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class ControllerReturnAspect
{
    @Around("cn.mabbit.mspc.core.consts.GlobalPointCuts.api()")
    public Object returning(ProceedingJoinPoint point)
            throws Throwable
    {
        Object result = point.proceed();

        responseJson(R.ok(result));
        return null;
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