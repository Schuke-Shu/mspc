package cn.mabbit.mspc.cache;

import cn.mabbit.mspc.core.CommonPoolProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-15 14:49
 */
@Getter
@Setter
@ToString(callSuper = true)
@ConfigurationProperties(CacheProperties.PREFIX)
public class CacheProperties extends CommonPoolProperties
{
    public static final String PREFIX = "common.cache";

    /**
     * 主机地址
     */
    private String host = "127.0.0.1";
    /**
     * 端口号
     */
    private Integer port = 6379;
    /**
     * 密码
     */
    private String password;
    private JsonTypeInfo.As serialType = EXISTING_PROPERTY;
}