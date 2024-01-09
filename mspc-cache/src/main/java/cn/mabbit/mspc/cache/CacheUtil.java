package cn.mabbit.mspc.cache;

import cn.mabbit.mspc.core.annotation.Util;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * <h2>缓存服务</h2>
 *
 * @Date 2023-12-20 20:21
 */
@Util
@Setter(onMethod_ = @Autowired)
public class CacheUtil
{
    /**
     * 默认15分钟
     */
    public static final long DEFAULT_SECONDS = 15 * 60;
    /**
     * 默认时间单位
     */
    public static final TimeUnit DEFAULT_UNIT = TimeUnit.SECONDS;

    private RedisTemplate<String, Object> redis;

    /**
     * 设置有效时间，默认单位为秒
     *
     * @param key  key
     * @param time 有效时间
     */
    public void expire(String key, long time)
    {
        expire(key, time, DEFAULT_UNIT);
    }

    /**
     * 设置有效时间
     *
     * @param key  key
     * @param time 有效时间
     * @param unit 时间单位
     */
    public void expire(String key, long time, TimeUnit unit)
    {
        redis.expire(key, time, unit);
    }

    /**
     * 设置缓存
     *
     * @param key   key
     * @param value value
     */
    public void set(String key, Object value)
    {
        ops().set(key, value);
    }

    /**
     * 设置缓存，同时设置有效时间
     *
     * @param key     key
     * @param value   value
     * @param seconds 有效时间（秒）
     */
    public void set(String key, Object value, long seconds)
    {
        ops().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     *
     * @param key key
     */
    public void remove(String key)
    {
        redis.delete(key);
    }

    /**
     * 获取缓存
     *
     * @param key key
     * @return value
     */
    public Object get(String key)
    {
        return ops().get(key);
    }

    /**
     * @param key key
     * @return key 是否存在
     */
    public boolean has(String key)
    {
        return Boolean.TRUE.equals(redis.hasKey(key));
    }

    private ValueOperations<String, Object> ops()
    {
        return redis.opsForValue();
    }
}