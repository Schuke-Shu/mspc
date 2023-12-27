package cn.mabbit.mspc.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * <h2>Spring工具类</h2>
 *
 * @Date 2023/9/20 11:13
 */
public abstract class SpringUtil
{
    /**
     * Spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory factory;

    /**
     * 通过bean名称获取bean
     *
     * @param name 指定bean名称
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public static Object getBean(String name)
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
    public static  <T> T getBean(String name, Class<T> type)
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
    public static Object getBean(String name, Object... args)
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
    public static  <T> T getBean(Class<T> type)
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
    public static  <T> T getBean(Class<T> type, Object... args)
            throws BeansException
    {
        return factory.getBean(type, args);
    }

    public static  <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType)
    {
        return factory.getBeanProvider(requiredType);
    }

    public static  <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType)
    {
        return factory.getBeanProvider(requiredType);
    }

    /**
     * 根据bean名称判断是否存在bean
     *
     * @param name bean名称
     * @return 指定bean是否存在
     */
    public static boolean containsBean(String name)
    {
        return factory.containsBean(name);
    }

    /**
     * 根据bean名称判断该bean是否为单例
     * @param name bean名称
     * @return 指定bean是否为单例
     * @throws NoSuchBeanDefinitionException 找不到该bean
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException
    {
        return factory.isSingleton(name);
    }

    /**
     * 根据bean名称判断该bean是否不为单例
     * @param name bean名称
     * @return 指定bean是否不为单例
     * @throws NoSuchBeanDefinitionException 找不到该bean
     */
    public static boolean isPrototype(String name) throws NoSuchBeanDefinitionException
    {
        return factory.isPrototype(name);
    }

    public static boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException
    {
        return factory.isTypeMatch(name, typeToMatch);
    }

    public static boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException
    {
        return factory.isTypeMatch(name, typeToMatch);
    }

    @Nullable
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException
    {
        return factory.getType(name);
    }

    @Nullable
    public static Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException
    {
        return factory.getType(name, allowFactoryBeanInit);
    }

    public static String[] getAliases(String name)
    {
        return factory.getAliases(name);
    }
}