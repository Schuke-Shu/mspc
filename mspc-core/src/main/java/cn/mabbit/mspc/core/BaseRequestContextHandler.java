package cn.mabbit.mspc.core;

import java.time.LocalDateTime;

/**
 * <h2>请求上下文处理器</h2>
 *
 * @Date 2024-01-04 14:51
 */
public abstract class BaseRequestContextHandler
{
    public static final String REQUEST_TIME = "request_time";

    protected static RequestContext context;

    public static void requestTime(LocalDateTime time)
    {
        context.put(REQUEST_TIME, time);
    }

    public static LocalDateTime requestTime()
    {
        return (LocalDateTime) context.get(REQUEST_TIME);
    }

    private static boolean initialized = false;
    public static void setContext(RequestContext ctx)
    {
        if (!initialized)
        {
            context = ctx;
            initialized = true;
        }
    }
}