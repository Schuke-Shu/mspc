package cn.mabbit.mspc.security;

/**
 * <h2>Security 常量池</h2>
 *
 * @Date 2023/12/21 16:40
 */
public interface SecurityConsts
{
    /**
     * Claims存储字段名：用户id
     */
    String CLAIMS_KEY_ID = "id";

    /**
     * Claims存储字段名：用户名
     */
    String CLAIMS_KEY_USERNAME = "username";

    /**
     * Claims存储字段名：用户手机号
     */
    String CLAIMS_KEY_PHONE = "phone";

    /**
     * Claims存储字段名：用户邮箱
     */
    String CLAIMS_KEY_EMAIL = "email";

    /**
     * Claims存储字段名：请求IP地址
     */
    String CLAIMS_KEY_IP = "ip";

    /**
     * Claims存储字段名：用户权限集合
     */
    String CLAIMS_KEY_AUTHORITIES = "authorities";

    /**
     * Claims存储字段名：token过期时间
     */
    String CLAIMS_KEY_EXPIRATION_TIME = "expirationTime";
}