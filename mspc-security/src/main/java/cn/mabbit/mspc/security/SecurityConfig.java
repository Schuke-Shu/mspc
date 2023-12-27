package cn.mabbit.mspc.security;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <h2>Security 模块配置</h2>
 *
 * @Date 2023/12/21 15:25
 */
@Configuration
@EnableWebSecurity
@Setter(onMethod_ = @Autowired)
@EnableConfigurationProperties(SecurityProperties.class)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig
{
    private SecurityProperties securityProperties;
    private TokenFilter tokenFilter;

    // 加密编码器
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception
    {
        return
                http
                        .authorizeHttpRequests(
                                auth ->
                                        auth
                                                // 放行白名单
                                                .requestMatchers(securityProperties.getWhiteList()).permitAll()
                                                // 其他任何请求需要通过认证
                                                .anyRequest().authenticated()
                        )
                        // 启用 Security 框架自带的 CorsFilter 过滤器，对 OPTIONS 请求放行
                        .cors(Customizer.withDefaults())
                        // 禁用“防止伪造的跨域攻击”防御机制
                        .csrf(CsrfConfigurer::disable)
                        .formLogin(FormLoginConfigurer::disable)
                        // 设置 Session 创建策略：从不创建
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                        // 将 token 过滤器置于 Spring Security 的“用户名密码认证信息过滤器”之前
                        .addFilterAt(
                                tokenFilter,
                                UsernamePasswordAuthenticationFilter.class
                        )
                        .build();
    }
}