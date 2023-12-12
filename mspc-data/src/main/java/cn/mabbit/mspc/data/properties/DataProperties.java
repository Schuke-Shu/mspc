package cn.mabbit.mspc.data.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h2>数据库配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-12 14:32
 */
@Getter
@Setter
@ConfigurationProperties(prefix = DataProperties.PREFIX)
public class DataProperties
        implements Serializable
{
    public static final String PREFIX = "common.data";

    @Serial
    private static final long serialVersionUID = 1L;

    private PageProperties page = new PageProperties();

    @Getter
    @Setter
    public static class PageProperties
    {
        private Boolean defaultCount = true;
    }
}