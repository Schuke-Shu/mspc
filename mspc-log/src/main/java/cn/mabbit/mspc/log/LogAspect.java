package cn.mabbit.mspc.log;

import cn.jruyi.core.util.ArrayUtil;
import cn.jruyi.core.util.ClassUtil;
import cn.mabbit.mspc.core.RequestContext;
import cn.mabbit.mspc.core.consts.KeyConsts;
import cn.mabbit.mspc.core.exception.BaseException;
import cn.mabbit.mspc.core.util.IpUtil;
import cn.mabbit.mspc.core.util.ServletUtil;
import cn.mabbit.mspc.core.web.ServiceCode;
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
import java.time.LocalDateTime;
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
        log.debug("日志模块上线");
    }

    @Around("@annotation(anno)")
    public Object log(ProceedingJoinPoint point, Log anno) throws Throwable
    {
        // 初始化日志对象
        SysLog record = initLog(anno);
        if (anno.recordParams()) recordParams(record, point);
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
        record.setCreateTime((LocalDateTime) RequestContext.get(KeyConsts.REQUEST_TIME));

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

    private void recordParams(SysLog record, ProceedingJoinPoint point)
    {
        Object[] args = point.getArgs();
        if (noArg(args)) return;

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

    private boolean noArg(Object[] args)
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
        TimeUnit unit = TimeUnit.MILLISECONDS;
        if (time > 1000 * 60 * 60) return unit.toHours(time) + "h";
        if (time > 1000 * 60) return unit.toMinutes(time) + "m";
        if (time > 1000) return unit.toSeconds(time) + "s";
        return time + "ms";
    }
}