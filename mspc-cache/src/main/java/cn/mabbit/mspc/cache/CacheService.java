package cn.mabbit.mspc.cache;

import com.alibaba.fastjson2.JSON;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <h2>缓存服务接口</h2>
 *
 * @author 一只枫兔
 * @Date 2023/12/20 20:21
 */
@Service
@Setter(onMethod_ = @Autowired)
public class CacheService
{
    private JedisPool pool;

    public void set(String key, Object value)
    {
        try (Jedis jedis = client())
        {
            jedis.set(key, JSON.toJSONString(value));
        }
    }

    public void setex(String key, Object value, long seconds)
    {
        try (Jedis jedis = client())
        {
            jedis.setex(key, seconds, JSON.toJSONString(value));
        }
    }

    public <T> T get(String key, Class<T> cls)
    {
        try (Jedis jedis = client())
        {
            return JSON.parseObject(jedis.get(key), cls);
        }
    }

    private Jedis client()
    {
        return pool.getResource();
    }
}