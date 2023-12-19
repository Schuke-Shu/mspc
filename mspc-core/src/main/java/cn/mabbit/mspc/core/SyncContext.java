package cn.mabbit.mspc.core;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <h2>请求上下文</h2>
 *
 * <p>利用 {@link ThreadLocal} 存储上下文，为线程安全的</p>
 *
 * <p>该上下文仅存在于控制器方法执行结束之前，控制器方法执行完毕后销毁</p>
 *
 * @see ApiAspect
 * @author 一只枫兔
 * @Date 2023-12-12 10:44
 */
@Slf4j
public class SyncContext
{
    private static final Context CT = new Context();

    private static Map<String, Object> local()
    {
        return CT.get();
    }
    static void clean()
    {
        CT.remove();
        log.trace("The current 'SyncContext' has been cleaned");
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
        log.trace("Add context: {}, value: {}", key, value);
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

    private static class Context
            extends ThreadLocal<Map<String, Object>>
    {
        @Override
        protected Map<String, Object> initialValue()
        {
            return new HashMap<>();
        }
    }
}