package cn.mabbit.mspc.core.web;

import cn.mabbit.mspc.core.consts.KeyConsts;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <h2>全局参数</h2>
 *
 * <p>全局参数仅存在于控制器方法执行期间，控制器方法执行完毕后销毁</p>
 *
 * @author 一只枫兔
 * @Date 2023-12-12 10:44
 * @see GlobalParams#handleParams(ProceedingJoinPoint)
 */
@Aspect
@Component
public class GlobalParams
{
    private static final ThreadLocal<Map<String, Object>> GLOBAL_PARAM = new ThreadLocal<>();

    @Around("cn.mabbit.mspc.core.consts.GlobalPointCuts.api()")
    public Object handleParams(ProceedingJoinPoint point)
            throws Throwable
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put(KeyConsts.REQUEST_TIME, LocalDateTime.now());
        GLOBAL_PARAM.set(map);

        try
        {
            return point.proceed();
        }
        finally
        {
            GLOBAL_PARAM.remove();
        }
    }

    private static Map<String, Object> local()
    {
        return GLOBAL_PARAM.get();
    }

    public static int size()
    {
        return local().size();
    }

    public static boolean isEmpty()
    {
        return local().isEmpty();
    }

    public static boolean containsKey(String key)
    {
        return local().containsKey(key);
    }

    public static boolean containsValue(Object value)
    {
        return local().containsValue(value);
    }

    public static Object get(String key)
    {
        return local().get(key);
    }

    public static Object put(String key, Object value)
    {
        return local().put(key, value);
    }

    public static Object remove(String key)
    {
        return local().remove(key);
    }

    public static void putAll(@NotNull Map<? extends String, ?> m)
    {
        local().putAll(m);
    }

    public static void clear()
    {
        local().clear();
    }

    public static Set<String> keySet()
    {
        return local().keySet();
    }

    public static Collection<Object> values()
    {
        return local().values();
    }

    public static Set<Map.Entry<String, Object>> entrySet()
    {
        return local().entrySet();
    }
}