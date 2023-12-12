package cn.mabbit.mspc.core.util;

import cn.mabbit.mspc.core.exception.ProjectException;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.function.Consumer;

/**
 * <h2>Servlet工具类</h2>
 *
 * @author 一只枫兔
 * @Date 2023/9/15 13:02
 */
@Slf4j
public abstract class ServletUtil
{
    public static ServletRequestAttributes getRequestAttributes()
    {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * @return 获取当前请求的request对象
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * @return 获取当前请求的response对象
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
     * @param key 参数键值对的key值
     * @return 参数
     */
    public static String getParameter(String key)
    {
        return getRequest().getParameter(key);
    }
}