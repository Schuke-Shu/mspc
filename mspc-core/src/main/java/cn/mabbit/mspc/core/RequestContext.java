package cn.mabbit.mspc.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <h2>请求上下文</h2>
 *
 * <p>利用 {@link ThreadLocal} 存储上下文，线程安全</p>
 *
 * <p>该上下文仅存在于控制器方法执行结束之前，控制器方法执行完毕后销毁，由 {@link ApiAspect} 控制</p>
 *
 * @Date 2023-12-12 10:44
 * @see ApiAspect
 */
@Slf4j
@Component("requestContext")
public class RequestContext implements Map<String, Object>, Closeable
{
    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(HashMap::new);

    public static Map<String, Object> local()
    {
        return CONTEXT.get();
    }

    @Override
    public void close()
    {
        log.debug("清空请求上下文");
        CONTEXT.remove();
    }

    @Override
    public int size()
    {
        return local().size();
    }

    @Override
    public boolean isEmpty()
    {
        return local().isEmpty();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return local().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return local().containsValue(value);
    }

    @Override
    public Object get(Object key)
    {
        Object value = local().get(key);
        log.trace("\n获取请求上下文：\nkey【{}】\nvalue【{}】", key, value);
        return value;
    }

    @Override
    public Object put(String key, Object value)
    {
        log.trace("\n存储请求上下文：\nkey【{}】\nvalue【{}】", key, value);
        return local().put(key, value);
    }

    @Override
    public Object remove(Object key)
    {
        return local().remove(key);
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ?> m)
    {
        local().putAll(m);
    }

    @Override
    public void clear()
    {
        local().clear();
    }

    @NonNull
    @Override
    public Set<String> keySet()
    {
        return local().keySet();
    }

    @NonNull
    @Override
    public Collection<Object> values()
    {
        return local().values();
    }

    @NonNull
    @Override
    public Set<Map.Entry<String, Object>> entrySet()
    {
        return local().entrySet();
    }
}