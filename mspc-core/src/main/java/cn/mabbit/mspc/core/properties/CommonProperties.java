package cn.mabbit.mspc.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * <h2>通用配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023/10/9 18:20
 */
@Getter
@Setter
@ConfigurationProperties(CommonProperties.PREFIX)
public class CommonProperties
        implements Serializable
{
    public static final String PREFIX = "common.core";

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 项目信息
     */
    private ProjectInfo projectInfo = new ProjectInfo();
    /**
     * 模块信息
     */
    private ModuleInfo moduleInfo = new ModuleInfo();

    @Getter
    @Setter
    public static class ProjectInfo
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
    public static class ModuleInfo
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
    }
}