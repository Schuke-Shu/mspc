package cn.mabbit.mspc.core.web;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h2>业务状态码</h2>
 *
 * @Date 2023/10/09 18:15
 */
public enum ServiceCode implements Serializable
{
    OK(0x0, "0", "请求成功"),
    ERR_UNKNOWN(0x1, "1", "未知错误"),

    /**
     * 系统错误
     */
    ERR_SYSTEM(0xA0000, "A0000", "系统错误"),
    ERR_DATABASE(0xA0200, "A0200", "数据库错误"),

    /**
     * 用户端错误
     */
    ERR_USER(0xB0000, "B0000", "用户端错误"),
    ERR_REGISTER(0xB0100, "B0100", "注册异常"),
    ERR_LOGIN(0xB0200, "B0200", "登录异常"),
    ERR_TOKEN_EXPIRED(0xB0201, "B0201", "Token 过期"),
    ERR_TOKEN_SIGNATURE(0xB0202, "B0202", "Token 签名错误"),
    ERR_TOKEN_MALFORMED(0xB0203, "B0203", "Token 格式错误"),
    ERR_ACCESS(0xB0300, "B0300", "访问异常"),
    ERR_INVALID(0xB0301, "B0301", "非法参数"),
    ERR_NOT_FOUND(0xB0302, "B0302", "无法获取请求结果"),
    ;

    @Serial
    private static final long serialVersionUID = 1L;

    private final int code;
    private final String hex;
    private final String msg;

    /**
     * 构造
     *
     * @param code 16进制状态码
     * @param msg  错误提示信息
     */
    ServiceCode(int code, String hex, String msg)
    {
        this.code = code;
        this.hex = hex;
        this.msg = msg;
    }

    /**
     * @return 16进制数字状态码
     */
    public int code()
    {
        return code;
    }

    /**
     * @return 16进制字符串状态码
     */
    public String hex()
    {
        return hex;
    }

    /**
     * @return 错误提示信息
     */
    public String msg()
    {
        return msg;
    }

    @Override
    public String toString()
    {
        return hex + " - " + msg;
    }
}