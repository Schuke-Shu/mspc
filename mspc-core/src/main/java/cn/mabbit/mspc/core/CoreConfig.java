package cn.mabbit.mspc.core;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h2>核心配置</h2>
 *
 * @Date 2023-10-09 18:15
 */
@Slf4j
@Configuration
@Setter(onMethod_ = @Autowired)
@EnableConfigurationProperties(CommonProperties.class)
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class CoreConfig implements WebMvcConfigurer
{
    public CoreConfig()
    {
        log.info("开始进行核心模块配置");
    }

    private RequestContext context;

    /**
     * 解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry
                .addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .maxAge(3600);
        log.debug("配置跨域");
    }

    /**
     * 配置快速失败
     *
     * @return 验证器
     */
    @Bean
    public Validator validator()
    {
        Validator validator = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();

        log.debug("配置快速失败");
        return validator;
    }

    @PostConstruct
    public void setContext()
    {
        BaseRequestContextHandler.setContext(context);
    }
}