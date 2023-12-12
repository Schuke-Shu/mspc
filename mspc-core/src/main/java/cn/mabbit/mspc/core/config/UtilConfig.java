package cn.mabbit.mspc.core.config;

import cn.mabbit.mspc.core.util.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * <h2>工具配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023/11/5 15:22
 */
@Configuration
public class UtilConfig
        implements BeanFactoryPostProcessor
{
    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory)
            throws BeansException
    {
        SpringUtil.setFactory(beanFactory);
    }
}