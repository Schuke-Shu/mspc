package cn.mabbit.mspc.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h2>通用配置</h2>
 *
 * @Date 2023/10/9 18:20
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(CommonProperties.PREFIX)
public class CommonProperties
        implements Serializable
{
    public static final String PREFIX = "common";

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 协议名称
     */
    private String protocol = "http";
    /**
     * 主机地址
     */
    private String host = "127.0.0.1";
    /**
     * 端口
     */
    private Integer port = 8080;
    /**
     * 项目信息
     */
    private ProjectProperties project;
    /**
     * 模块信息（微服务项目使用）
     */
    private ModuleProperties module;

    @Getter
    @Setter
    public static class ProjectProperties
            implements Serializable
    {
        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 名称
         */
        private String name;
        /**
         * 版本
         */
        private String version;
        /**
         * 描述
         */
        private String description;
    }

    @Getter
    @Setter
    public static class ModuleProperties
            implements Serializable
    {
        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 名称
         */
        private String name;
        /**
         * 描述
         */
        private String description;
        /**
         * url
         */
        private String url;
    }
}