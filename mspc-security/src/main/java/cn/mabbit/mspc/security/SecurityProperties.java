package cn.mabbit.mspc.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h2>安全配置</h2>
 *
 * @Date 2023/12/21 15:29
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(SecurityProperties.PREFIX)
public class SecurityProperties implements Serializable
{
    public static final String PREFIX = "common.security";

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * uri白名单
     */
    private String[] whiteList = {};

    /**
     * token 配置
     */
    private TokenProperties token;

    @Getter
    @Setter
    @ToString
    public static class TokenProperties
            implements Serializable
    {
        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 该模块是否只做 token 有效性验证（一般网关模块为 true）
         */
        private Boolean onlyValidityVerification = false;
        /**
         * token签名算法
         */
        private String algorithm = "HS256";
        /**
         * token类型
         */
        private String type = "JWT";
        /**
         * 解析和生成token使用的key
         */
        private String secretKey = "RedLeafGarden-JsonWebToken-SecretKey";
        /**
         * token有效时长（单位：分钟）
         */
        private Integer usableMinutes = 10080;
        /**
         * 长度下限
         */
        private Integer minLength = 105;
        /**
         * 存放token的请求头的名称
         */
        private String header = "Authorization";
        /**
         * token可刷新临期时间（单位：分钟），临期时间低于该值才可刷新
         */
        private Integer refreshAllowTime = 1500;
    }
}