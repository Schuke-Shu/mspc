package cn.mabbit.mspc.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <h2>通用连接池配置类</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-15 15:32
 */
@Getter
@Setter
@ToString
public abstract class CommonPoolProperties implements Serializable
{
    /**
     * 最大连接数
     */
    private Integer maxTotal = 30;
    /**
     * 最大空闲连接数
     */
    private Integer maxIdle = 10;
    /**
     * 每次释放连接的最大数目
     */
    private Integer numTestsPerEvictionRun = 1024;
    /**
     * 释放连接的扫描间隔（毫秒）
     */
    private Integer timeBetweenEvictionRunsMillis = 30000;
    /**
     * 连接最小空闲时间
     */
    private Integer minEvictableIdleTimeMillis = 1800000;
    /**
     * 连接空闲多久后释放，当空闲时间>该值 且 空闲连接>最大空闲连接数 时直接释放
     */
    private Integer softMinEvictableIdleTimeMillis = 10000;
    /**
     * 获取连接时的最大等待毫秒数，小于零：阻塞不确定的时间，默认-1
     */
    private Integer maxWaitMillis = 1500;
    /**
     * 在获取连接的时候检查有效性
     */
    private Boolean testOnBorrow = true;
    /**
     * 在空闲时检查有效性
     */
    private Boolean testWhileIdle = true;
    /**
     * 连接耗尽时是否阻塞，false报异常，ture阻塞直到超时
     */
    private Boolean blockWhenExhausted = false;
}