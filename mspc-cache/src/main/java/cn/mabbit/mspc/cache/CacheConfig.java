package cn.mabbit.mspc.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <h2>缓存配置</h2>
 *
 * @Date 2023-12-15 10:56
 */
@Slf4j
@EnableCaching
@Configuration
@Setter(onMethod_ = @Autowired)
public class CacheConfig
{
    public CacheConfig()
    {
        log.info("Start configuring the cache");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<String, Object> redis = new RedisTemplate<>();
        redis.setConnectionFactory(factory);



        Jackson2JsonRedisSerializer<Object> jsonSerializer =
                // Json 序列化
                new Jackson2JsonRedisSerializer<>(
                        new ObjectMapper()
                                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                                // 替换 enableDefaultTyping 方法
                                .activateDefaultTyping(
                                        LaissezFaireSubTypeValidator.instance,
                                        ObjectMapper.DefaultTyping.NON_FINAL,
                                        JsonTypeInfo.As.WRAPPER_ARRAY
                                ),
                        Object.class
                );
        // String 序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        redis.setKeySerializer(stringSerializer);
        redis.setHashKeySerializer(stringSerializer);
        redis.setStringSerializer(stringSerializer);
        redis.setValueSerializer(jsonSerializer);
        redis.setHashValueSerializer(jsonSerializer);
        redis.afterPropertiesSet();

        log.debug("Configured [RedisTemplate]");
        return redis;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory, RedisTemplate<?, ?> redisTemplate)
    {
        RedisCacheManager manager = new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(factory),
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(
                                RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(redisTemplate.getStringSerializer())
                        )
                        .serializeValuesWith(
                                RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(redisTemplate.getValueSerializer())
                        )
        );

        log.debug("Configured [RedisCacheManager]");
        return manager;
    }
}