package cn.mabbit.mspc.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <h2>{@link Jedis} 工具</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-15 15:36
 */
public class JedisUtil
{
    private static JedisPool pool;

    /**
     * @return Jedis 客户端
     */
    public static Jedis client()
    {
        return pool.getResource();
    }
}