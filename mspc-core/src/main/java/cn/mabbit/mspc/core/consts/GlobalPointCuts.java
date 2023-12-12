package cn.mabbit.mspc.core.consts;

import org.aspectj.lang.annotation.Pointcut;

/**
 * <h2>全局切点池</h2>
 *
 * @author 一只枫兔
 * @Date 2023-11-28 16:12
 */
public class GlobalPointCuts
{
    /**
     * 控制器接口 public 方法
     */
    @Pointcut("execution(public * *..controller.*Controller.*(..))")
    public void api() {}
}