package cn.mabbit.mspc.cache;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <h2>缓存服务接口实现类</h2>
 *
 * @author 一只枫兔
 * @Date 2023/12/20 20:21
 */
@Service
@Setter(onMethod_ = @Autowired)
public class CacheServiceImpl implements CacheService
{
    private RedisTemplate<String, Object> redis;

    @Override
    public void expire(String key, long time, TimeUnit unit)
    {
        redis.expire(key, time, unit);
    }

    @Override
    public void set(String key, Object value)
    {
        ops().set(key, value);
    }

    @Override
    public void set(String key, Object value, long seconds)
    {
        ops().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key)
    {
        redis.delete(key);
    }

    @Override
    public Object get(String key)
    {
        return ops().get(key);
    }

    @Override
    public boolean has(String key)
    {
        return Boolean.TRUE.equals(redis.hasKey(key));
    }

    private ValueOperations<String, Object> ops()
    {
        return redis.opsForValue();
    }
}