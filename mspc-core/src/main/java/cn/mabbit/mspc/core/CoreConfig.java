package cn.mabbit.mspc.core;

import cn.mabbit.mspc.core.exception.ProjectException;
import cn.mabbit.mspc.core.util.SpringUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Field;

/**
 * <h2>核心配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023/10/09 18:15
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties(CommonProperties.class)
public class CoreConfig
        implements WebMvcConfigurer, BeanFactoryPostProcessor
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
        Validator validator = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();

        log.info("Configured fast-fail of validator");
        return validator;
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory)
            throws BeansException
    {
        // TODO Mdk4j 反射工具
        try
        {
            Field factory = SpringUtil.class.getDeclaredField("factory");
            factory.setAccessible(true);
            factory.set(null, beanFactory);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            throw new ProjectException(e);
        }
        log.info("Configured spring-util");
    }
}