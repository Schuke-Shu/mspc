package cn.mabbit.mspc.log;

import cn.jruyi.core.util.ArrayUtil;
import cn.jruyi.core.util.ClassUtil;
import cn.mabbit.mspc.core.ThreadContext;
import cn.mabbit.mspc.core.consts.KeyConsts;
import cn.mabbit.mspc.core.exception.BaseException;
import cn.mabbit.mspc.core.util.IpUtil;
import cn.mabbit.mspc.core.util.ServletUtil;
import cn.mabbit.mspc.core.web.ServiceCode;
import cn.mabbit.mspc.log.enums.Status;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.PropertyFilter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * <h2>日志 AOP</h2>
 *
 * @Date 2023-12-13 12:44
 */
@Slf4j
@Aspect
@Component
@Setter(onMethod_ = @Autowired)
@ConditionalOnBean(LogService.class)
public class LogAspect
{
    private LogService service;

    public LogAspect()
    {
        log.debug("日志模块上线");
    }

    @Around("@annotation(anno)")
    public Object log(ProceedingJoinPoint point, Log anno) throws Throwable
    {
        // 初始化日志对象
        SysLog record = initLog(anno);
        if (anno.recordParams()) recordParams(record, point.getArgs());
        // 声明结果
        Object result;

        long start = System.currentTimeMillis();
        try
        {
            // 放行
            result = point.proceed();
            // api 请求成功，记录后续结果
            success(record, anno, result);
        }
        catch (Throwable e)
        {
            recordError(record, e);
            throw e;
        }
        finally
        {
            record.setCastTime(
                    computeCastTime(
                            System.currentTimeMillis() - start
                    )
            );
            log.debug("日志：{}", record);
            service.record(record);
        }

        return result;
    }

    private SysLog initLog(Log anno)
    {
        log.debug("初始化日志对象");
        SysLog record = new SysLog();

        record.setTitle(anno.title());
        record.setDescription(anno.description());
        record.setOperatorType(anno.operation());
        record.setMethod(ServletUtil.getMethod());
        record.setUri(ServletUtil.getRequestURI());
        record.setIp(IpUtil.getIp());
        record.setBusinessType(anno.businessType());
        record.setCreateTime((LocalDateTime) ThreadContext.get(KeyConsts.REQUEST_TIME));

        return record;
    }

    private void success(SysLog record, Log anno, Object result)
    {
        if (anno.recordResult() && result != null)
            record.setResult(JSON.toJSONString(result));
        record.setStatus(Status.SUCCESS);
    }

    private void recordError(SysLog record, Throwable e)
    {
        // TODO JRuyi ClassUtil
        if (e instanceof BaseException base)
        {
            ServiceCode code = base.code();
            record.setErrorCode(code);
            record.setErrorMsg(code.msg());
        }

        record.setErrorType(ClassUtil.getTypeName(e));
        record.setStatus(Status.FAILED);
    }

    private void recordParams(SysLog record, Object[] args)
    {
        if (ArrayUtil.isEmpty(args)) return;

        record.setParams(
                JSON.toJSONString(
                        Arrays
                                .stream(args)
                                .filter(arg -> arg != null && !arg.getClass().isAnnotationPresent(LogIgnore.class))
                                .toArray(),
                        (PropertyFilter) (obj, name, value) -> value == null || !value.getClass().isAnnotationPresent(LogIgnore.class)
                )
        );
    }

    private String computeCastTime(long time)
    {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        if (time > 1000 * 60 * 60) return unit.toHours(time) + "h";
        if (time > 1000 * 60) return unit.toMinutes(time) + "m";
        if (time > 1000) return unit.toSeconds(time) + "s";
        return time + "ms";
    }
}