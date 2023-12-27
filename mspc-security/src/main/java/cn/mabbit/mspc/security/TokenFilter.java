package cn.mabbit.mspc.security;

import cn.jruyi.core.util.StringUtil;
import cn.mabbit.mspc.core.util.ServletUtil;
import cn.mabbit.mspc.core.web.R;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <h2>token 解析过滤器</h2>
 *
 * @Date 2023/12/21 16:07
 */
@Slf4j
@Component
@Setter(onMethod_ = @Autowired)
public class TokenFilter
        extends OncePerRequestFilter
        implements SecurityConsts
{
    private SecurityService service;
    private SecurityProperties properties;

    @Override
    protected void doFilterInternal(
            HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain
    )
            throws ServletException, IOException
    {
        // 清空SecurityContext，强制所有请求都必须携带JWT
        SecurityContextHolder.clearContext();

        SecurityProperties.TokenProperties props = properties.getToken();
        // 从请求头获取token
        String token = req.getHeader(props.getHeader());

        if (!valid(token))
        {
            log.debug("Token parsed failed, to next...");
            // token无效，放行
            chain.doFilter(req, res);
            return;
        }

        Claims claims;
        try
        {
            // token有效，准备解析
            claims = service.parse(token);
        }
        catch (SecurityServiceException e)
        {
            ServletUtil.responseJson(R.fail(e));
            return;
        }

        // 从Claims中获取数据
        Principal principal = new Principal(claims);

        // 创建Authentication对象
        Authentication auth = new AuthToken(
                // 封装Principal（当事人）对象
                principal,
                // 解析json形式的权限集合
                JSON.parseArray(
                        claims.get(CLAIMS_KEY_AUTHORITIES, String.class),
                        SimpleGrantedAuthority.class
                )
        );

        log.debug("Login authentication: {}", auth);

        // 存入到SecurityContext中
        service.setAuthentication(auth);

        // 放行
        log.debug("Token parsed success, to next...");
        chain.doFilter(req, res);
    }

    private boolean valid(String token)
    {
        boolean valid = !StringUtil.isBlank(token) && token.length() < properties.getToken().getMinLength();

        if (!valid) log.trace("Token is invalid, token: {}", token);
        return valid;
    }
}