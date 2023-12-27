package cn.mabbit.mspc.cache;

import java.util.concurrent.TimeUnit;

/**
 * <h2>缓存服务接口</h2>
 *
 * @Date 2023/12/21 8:36
 */
public interface CacheService
{
    /**
     * 默认15分钟
     */
    long DEFAULT_SECONDS = 15 * 60;

    /**
     * 默认时间单位
     */
    TimeUnit DEFAULT_UNIT = TimeUnit.SECONDS;

    /**
     * 设置有效时间，默认单位为秒
     *
     * @param key  key
     * @param time 有效时间
     */
    default void expire(String key, long time)
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
    void expire(String key, long time, TimeUnit unit);

    /**
     * 新增缓存
     *
     * @param key   key
     * @param value value
     */
    void set(String key, Object value);

    /**
     * 新增缓存，同时设置有效时间
     *
     * @param key     key
     * @param value   value
     * @param seconds 有效时间（秒）
     */
    void set(String key, Object value, long seconds);

    /**
     * 删除缓存
     *
     * @param key key
     */
    void remove(String key);

    /**
     * 获取缓存
     *
     * @param key key
     * @return value
     */
    Object get(String key);

    /**
     * @param key key
     * @return key 是否存在
     */
    boolean has(String key);
}