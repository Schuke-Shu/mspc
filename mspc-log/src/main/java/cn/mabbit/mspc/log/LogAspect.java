package cn.mabbit.mspc.log;

import cn.jruyi.core.util.ArrayUtil;
import cn.jruyi.core.util.ClassUtil;
import cn.mabbit.mspc.core.BaseRequestContextHandler;
import cn.mabbit.mspc.core.exception.BaseException;
import cn.mabbit.mspc.core.util.IpUtil;
import cn.mabbit.mspc.core.util.ServletUtil;
import cn.mabbit.mspc.log.enums.Status;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.PropertyFilter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.StringJoiner;
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
        log.info("日志模块上线");
    }

    @Around(value = "@annotation(log)")
    public Object log(ProceedingJoinPoint point, Log log) throws Throwable
    {
        // 初始化日志对象
        SysLog record = initLog(log);
        if (log.recordParams()) recordParams(record, point);
        // 声明结果
        Object result;

        long start = System.currentTimeMillis();
        try
        {
            // 放行
            result = point.proceed();
            // api 请求成功，记录后续结果
            success(record, log, result);
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
            LogAspect.log.trace("日志：{}", record);
            service.record(record);
        }

        return result;
    }

    private SysLog initLog(Log log)
    {
        SysLog record = new SysLog();

        record.setTitle(log.title());
        record.setDescription(log.description());
        record.setOperatorType(log.operation());
        record.setMethod(ServletUtil.getMethod());
        record.setUri(ServletUtil.getRequestURI());
        record.setIp(IpUtil.getIp());
        record.setBusinessType(log.businessType());
        record.setCreateTime(BaseRequestContextHandler.requestTime());

        LogAspect.log.debug("初始化日志对象: \n{}", record);
        return record;
    }

    private void success(SysLog record, Log log, Object result)
    {
        if (log.recordResult() && result != null)
            record.setResult(JSON.toJSONString(result));
        record.setStatus(Status.SUCCESS);
    }

    private void recordError(SysLog record, Throwable e)
    {
        // TODO JRuyi ClassUtil
        if (e instanceof BaseException base)
        {
            record.setErrorCode(base.code());
            record.setErrorMsg(base.code().msg());
        }

        record.setErrorType(ClassUtil.getTypeName(e));
        record.setStatus(Status.FAILED);
    }

    private void recordParams(SysLog record, ProceedingJoinPoint point)
    {
        Object[] args = point.getArgs();
        if (isNonArg(args)) return;

        StringJoiner joiner = new StringJoiner(",");
        // TODO JRuyi 反射
        Parameter[] parameters = ((MethodSignature) point.getSignature()).getMethod().getParameters();
        for (int i = 0; i < args.length; i++)
        {
            if (parameters[i].getAnnotatedType().getAnnotation(LogIgnore.class) != null)
                continue;

            joiner.add(
                    parameters[i].getName() +
                    ':' +
                    JSON.toJSONString(
                            args[i],
                            (PropertyFilter) (obj, name, value) ->
                                    value == null || !value.getClass().isAnnotationPresent(LogIgnore.class),
                            JSONWriter.Feature.BeanToArray
                    )
            );
        }

        record.setParams(joiner.toString());
    }

    private boolean isNonArg(Object[] args)
    {
        if (ArrayUtil.isEmpty(args)) return true;

        boolean isEmpty = true;
        for (Object o : args)
            if (o != null)
            {
                isEmpty = false;
                break;
            }
        return isEmpty;
    }

    private String computeCastTime(long time)
    {
        // TODO JRuyi TimeUtil
        TimeUnit unit = TimeUnit.MILLISECONDS;
        if (time > 1000 * 60 * 60) return unit.toHours(time) + "h";
        if (time > 1000 * 60) return unit.toMinutes(time) + "m";
        if (time > 1000) return unit.toSeconds(time) + "s";
        return time + "ms";
    }
}