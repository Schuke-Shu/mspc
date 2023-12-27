package cn.mabbit.mspc.security;

import cn.jruyi.core.lang.Assert;
import cn.mabbit.mspc.core.exception.ServiceException;
import cn.mabbit.mspc.core.web.ServiceCode;
import io.jsonwebtoken.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * <h2></h2>
 *
 * @Date 2023/12/21 16:14
 */
@Slf4j
@Service
@Setter(onMethod_ = @Autowired)
public abstract class SecurityService
{
    private SecurityProperties properties;

    /**
     * @return {@link SecurityContext}
     */
    public static SecurityContext getContext()
    {
        return SecurityContextHolder.getContext();
    }

    /**
     * @return {@link Authentication}
     */
    public Authentication getAuthentication()
    {
        return getContext().getAuthentication();
    }

    /**
     * @param authentication {@link Authentication}
     */
    public void setAuthentication(Authentication authentication)
    {
        getContext().setAuthentication(authentication);
    }

    /**
     * @return {@link Principal}
     */
    public Principal getPrincipal()
    {
        return (Principal) getAuthentication().getPrincipal();
    }

    /**
     * 生成token
     *
     * @param handleClaims 设置主体数据
     * @param expire       过期时间
     * @param algorithm    签名算法
     * @param type         token类型
     * @param secretKey    token密钥
     * @return token
     */
    public String generate(
            Consumer<Map<String, Object>> handleClaims,
            Date expire,
            String algorithm,
            String type,
            String secretKey
    )
    {
        // TODO JRuyi Assert.notBlank
//        Assert.notBlank(algorithm, () -> String.format(PROPERTIES_ERROR_PATTERN, SecurityProperties.TokenProperties.class, "algorithm"));
//        Assert.notBlank(type, () -> String.format(PROPERTIES_ERROR_PATTERN, SecurityProperties.TokenProperties.class, "type"));
//        Assert.notBlank(secretKey, () -> String.format(PROPERTIES_ERROR_PATTERN, SecurityProperties.TokenProperties.class, "secretKey"));
        Assert.isTrue(expire.before(new Date()), () -> "Timeout setting is invalid");

        Map<String, Object> claims = new HashMap<>();
        handleClaims.accept(claims);

        return
                Jwts
                        // 获取JwtBuilder，用于构建token
                        .builder()

                        // 配置Header
                        .setHeaderParam("alg", algorithm)
                        .setHeaderParam("typ", type)

                        // 配置payload（存入数据）
                        .setClaims(claims)

                        // 配置Signature
                        .setExpiration(expire)              // 配置token过期时间
                        .signWith(
                                SignatureAlgorithm.forName(
                                        algorithm
                                ),
                                secretKey
                        )                                   // 配置算法和密钥

                        // 获取JWT
                        .compact();
    }

    /**
     * 生成token，除设置主体函数与过期时间外使用配置数据
     *
     * @param handleClaims 设置主体数据
     * @param expire       过期时间
     * @return token
     */
    public String generate(Consumer<Map<String, Object>> handleClaims, Date expire)
    {
        SecurityProperties.TokenProperties props = properties.getToken();
        return generate(
                handleClaims,
                expire,
                props.getAlgorithm(),
                props.getType(),
                props.getSecretKey()
        );
    }

    /**
     * 生成token，除设置主体函数外全部使用配置数据
     *
     * @param handleClaims 设置主体数据
     * @return token
     */
    public String generate(Consumer<Map<String, Object>> handleClaims)
    {
        SecurityProperties.TokenProperties props = properties.getToken();
        return generate(
                handleClaims,
                new Date(
                        System.currentTimeMillis() +
                        MILLISECONDS.convert(
                                props.getUsableMinutes(),
                                TimeUnit.MINUTES
                        )
                ),
                props.getAlgorithm(),
                props.getType(),
                props.getSecretKey()
        );
    }

    /**
     * 解析token
     *
     * @param token tokenUtil
     * @return {@link Claims}
     */
    public Claims parse(String token)
    {
        SecurityProperties.TokenProperties props = properties.getToken();
        try
        {
            return
                    Jwts
                            .parser()
                            .setSigningKey(props.getSecretKey())
                            .parseClaimsJws(token)
                            .getBody();
        }
        catch (Exception e)
        {
            ServiceCode code =
                    switch (e)
                    {
                        case ExpiredJwtException ignored -> ServiceCode.ERR_TOKEN_EXPIRED;
                        case SignatureException ignored -> ServiceCode.ERR_TOKEN_SIGNATURE;
                        case MalformedJwtException ignored -> ServiceCode.ERR_TOKEN_MALFORMED;
                        default -> ServiceCode.ERR_UNKNOWN;
                    };

            // 未知错误
            if (code == ServiceCode.ERR_UNKNOWN)
                log.error("Unknown error, -- {}: {}", e.getClass().getSimpleName(), e.getMessage());
            else
                log.debug(code.msg());

            throw new ServiceException(code);
        }
    }
}