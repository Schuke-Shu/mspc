package cn.mabbit.mspc.data.config;

import cn.mabbit.mspc.data.interceptor.TimeInterceptor;
import cn.mabbit.mspc.data.properties.DataProperties;
import cn.mabbit.mspc.data.util.PageUtil;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <h2>数据库模块配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-12 8:35
 */
@Configuration
@MapperScan("**.mapper")
@Setter(onMethod_ = @Autowired)
@EnableConfigurationProperties(DataProperties.class)
public class DataConfig
        implements InitializingBean
{
    private List<SqlSessionFactory> factories;
    private DataProperties properties;

    // 配置 Mybatis 拦截器
    @PostConstruct
    public void addInterceptor()
    {
        TimeInterceptor interceptor = new TimeInterceptor();
        for (SqlSessionFactory factory : factories)
            factory
                    .getConfiguration()
                    .addInterceptor(interceptor);
    }

    @Override
    public void afterPropertiesSet()
    {
        PageUtil.setDefaultCount(
                properties
                        .getPage()
                        .getDefaultCount()
        );
    }
}