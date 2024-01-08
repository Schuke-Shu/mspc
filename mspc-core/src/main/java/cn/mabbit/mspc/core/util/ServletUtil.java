package cn.mabbit.mspc.core.util;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

/**
 * <h2>Servlet 工具类</h2>
 *
 * @Date 2023/9/15 13:02
 */
public abstract class ServletUtil
{
    public static void responseJson(Object data)
    {
        HttpServletResponse res = ServletUtil.getResponse();

        res.setContentType("application/json;charset=utf-8");

        try (PrintWriter writer = res.getWriter())
        {
            writer.write(JSON.toJSONString(data));
            writer.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * @return 当前请求的request对象
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * @return 当前请求的response对象
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * @return {@link HttpServletRequest#getMethod()}
     */
    public static String getMethod()
    {
        return getRequest().getMethod();
    }

    /**
     * @return {@link HttpServletRequest#getRequestURI()}
     */
    public static String getRequestURI()
    {
        return getRequest().getRequestURI();
    }

    /**
     * @param s 请求头key值
     * @return {@link HttpServletRequest#getHeader(String)}
     */
    public static String getHeader(String s)
    {
        return getRequest().getHeader(s);
    }

    /**
     * @param s 请求头key值
     * @return {@link HttpServletRequest#getHeaders(String)}
     */
    public static Enumeration<String> getHeaders(String s)
    {
        return getRequest().getHeaders(s);
    }

    /**
     * @return 参数 Map
     */
    public static Map<String, String[]> getParameterMap()
    {
        return getRequest().getParameterMap();
    }

    /**
     * @param key 参数键值对的key值
     * @return 参数
     */
    public static String getParameter(String key)
    {
        return getRequest().getParameter(key);
    }
}