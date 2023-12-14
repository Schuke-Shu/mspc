package cn.mabbit.mspc.core.web;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h2>业务状态码</h2>
 *
 * @author 一只枫兔
 * @Date 2023/10/09 18:15
 */
public final class ServiceCode
        implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码（16进制代码）
     */
    private final int code;
    /**
     * 错误提示信息
     */
    private final String msg;

    /**
     * 构造
     *
     * @param code 状态码（16进制代码）
     * @param msg  错误提示信息
     */
    ServiceCode(String code, String msg)
    {
        this.code = Integer.parseInt(code, 16);
        this.msg = msg;
    }

    /**
     * 创建一个状态码对象
     *
     * @param code 状态码（16进制代码）
     * @param msg  错误提示信息
     * @return 业务状态码对象
     */
    public static ServiceCode newCode(String code, String msg)
    {
        return new ServiceCode(code, msg);
    }

    /**
     * @return 状态码（16进制代码）
     */
    public int code()
    {
        return code;
    }

    /**
     * @return 错误提示信息
     */
    public String msg()
    {
        return msg;
    }
}