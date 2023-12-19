package cn.mabbit.mspc.cache;

import cn.mabbit.mspc.core.exception.ProjectException;

/**
 * <h2>缓存异常</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-15 11:06
 */
public class CacheException
        extends ProjectException
{
    public CacheException() {}

    public CacheException(String message)
    {
        super(message);
    }
}