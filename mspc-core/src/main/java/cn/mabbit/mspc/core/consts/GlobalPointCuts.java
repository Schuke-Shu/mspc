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
     * 被 mapping 注解标记的方法
     */
    @Pointcut(
            "execution(@org.springframework.web.bind.annotation.RequestMapping * *..*(..)) ||" +
            "execution(@org.springframework.web.bind.annotation.GetMapping * *..*(..)) ||" +
            "execution(@org.springframework.web.bind.annotation.PostMapping * *..*(..)) ||" +
            "execution(@org.springframework.web.bind.annotation.PutMapping * *..*(..)) ||" +
            "execution(@org.springframework.web.bind.annotation.DeleteMapping * *..*(..)) ||" +
            "execution(@org.springframework.web.bind.annotation.PatchMapping * *..*(..))"
    )
    public void mapping() {}

    /**
     * 控制器方法
     */
    @Pointcut("execution(* *..controller.*Controller.*(..))")
    public void controller() {}

    /**
     * API 接口方法
     */
    @Pointcut("controller() && mapping()")
    public void api() {}
}