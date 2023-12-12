package cn.mabbit.mspc.core.config;

import cn.mabbit.mspc.core.properties.CommonProperties;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h2>核心配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023/10/09 18:15
 */
@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties(CommonProperties.class)
public class CoreConfig
        implements WebMvcConfigurer
{
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
    }

    /**
     * 配置快速失败
     *
     * @return 验证器
     */
    @Bean
    public Validator validator()
    {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }
}