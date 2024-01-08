package cn.mabbit.mspc.core.util;

import cn.mabbit.mspc.core.annotation.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.ResolvableType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * <h2>Spring 工具类</h2>
 *
 * @Date 2023/9/20 11:13
 */
@Util
@Slf4j
public class SpringUtil implements BeanFactoryPostProcessor
{
    /**
     * Spring应用上下文环境
     */
    private ConfigurableListableBeanFactory factory;

    public <T> T getProxy(T ignoredTarget)
    {
        //noinspection unchecked
        return (T) AopContext.currentProxy();
    }

    /**
     * 通过bean名称获取bean
     *
     * @param name 指定bean名称
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public Object getBean(String name)
            throws BeansException
    {
        return factory.getBean(name);
    }

    /**
     * 通过bean名称与类型获取bean
     *
     * @param name 指定bean名称
     * @param type 指定bean类型
     * @param <T>  bean类型
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public <T> T getBean(String name, Class<T> type)
            throws BeansException
    {
        return factory.getBean(name, type);
    }

    /**
     * 通过bean名称与指定有参构造参数获取bean
     *
     * @param name 指定bean名称
     * @param args 指定有参构造参数
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public Object getBean(String name, Object... args)
            throws BeansException
    {
        return factory.getBean(name, args);
    }

    /**
     * 通过bean类型获取bean
     *
     * @param type 指定bean类型
     * @param <T>  bean类型
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public <T> T getBean(Class<T> type)
            throws BeansException
    {
        return factory.getBean(type);
    }

    /**
     * 通过bean类型与指定有参构造参数获取bean
     *
     * @param type 指定bean类型
     * @param args 指定有参构造参数
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public <T> T getBean(Class<T> type, Object... args)
            throws BeansException
    {
        return factory.getBean(type, args);
    }

    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType)
    {
        return factory.getBeanProvider(requiredType);
    }

    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType)
    {
        return factory.getBeanProvider(requiredType);
    }

    /**
     * 根据bean名称判断是否存在bean
     *
     * @param name bean名称
     * @return 指定bean是否存在
     */
    public boolean containsBean(String name)
    {
        return factory.containsBean(name);
    }

    /**
     * 根据bean名称判断该bean是否为单例
     *
     * @param name bean名称
     * @return 指定bean是否为单例
     * @throws NoSuchBeanDefinitionException 找不到该bean
     */
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException
    {
        return factory.isSingleton(name);
    }

    /**
     * 根据bean名称判断该bean是否不为单例
     *
     * @param name bean名称
     * @return 指定bean是否不为单例
     * @throws NoSuchBeanDefinitionException 找不到该bean
     */
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException
    {
        return factory.isPrototype(name);
    }

    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException
    {
        return factory.isTypeMatch(name, typeToMatch);
    }

    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException
    {
        return factory.isTypeMatch(name, typeToMatch);
    }

    @Nullable
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException
    {
        return factory.getType(name);
    }

    @Nullable
    public Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException
    {
        return factory.getType(name, allowFactoryBeanInit);
    }

    public String[] getAliases(String name)
    {
        return factory.getAliases(name);
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory factory) throws BeansException
    {
        log.debug("初始化 Spring 工具");
        this.factory = factory;
    }
}