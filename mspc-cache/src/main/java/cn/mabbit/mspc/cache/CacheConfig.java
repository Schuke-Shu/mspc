package cn.mabbit.mspc.cache;

import cn.mabbit.mspc.core.exception.ProjectException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Field;
import java.time.Duration;

/**
 * <h2>缓存配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-15 10:56
 */
@Slf4j
@EnableCaching
@Configuration
@Setter(onMethod_ = @Autowired)
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig
{
    private CacheProperties properties;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<String, Object> redis = new RedisTemplate<>();
        redis.setConnectionFactory(factory);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 替换 enableDefaultTyping 方法
        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY
        );
        // Json 序列化
        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(mapper, Object.class);
        // String 序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        redis.setKeySerializer(stringSerializer);
        redis.setHashKeySerializer(stringSerializer);
        redis.setValueSerializer(jsonSerializer);
        redis.setHashValueSerializer(jsonSerializer);
        redis.afterPropertiesSet();

        log.info("Configured [RedisTemplate]");
        return redis;
    }

    @PostConstruct
    public void initUtil()
    {
        String host = properties.getHost();
        Integer port = properties.getPort();

        // TODO Mdk4j 反射工具
        try
        {
            Field factory = JedisUtil.class.getDeclaredField("pool");
            factory.setAccessible(true);
            factory.set(null, new JedisPool(initConfig(), host, port));
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            throw new ProjectException(e);
        }

        log.debug("Configured jedis pool, address:[{}:{}]", host, port);
    }

    private JedisPoolConfig initConfig()
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(properties.getMaxTotal());
        config.setMaxIdle(properties.getMaxIdle());
        config.setNumTestsPerEvictionRun(properties.getNumTestsPerEvictionRun());
        config.setTimeBetweenEvictionRuns(duration(properties.getTimeBetweenEvictionRunsMillis()));
        config.setMinEvictableIdleDuration(duration(properties.getMinEvictableIdleTimeMillis()));
        config.setSoftMinEvictableIdleDuration(duration(properties.getSoftMinEvictableIdleTimeMillis()));
        config.setMaxWait(duration(properties.getMaxWaitMillis()));
        config.setTestOnBorrow(properties.getTestOnBorrow());
        config.setTestWhileIdle(properties.getTestWhileIdle());
        config.setBlockWhenExhausted(properties.getBlockWhenExhausted());

        log.debug("Jedis pool config:\n{}", config);
        return config;
    }

    private static Duration duration(long millis)
    {
        return Duration.ofMillis(millis);
    }
}