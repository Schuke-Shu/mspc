package cn.mabbit.mspc.log;

import cn.mabbit.mspc.core.web.ServiceCode;
import cn.mabbit.mspc.data.pojo.BasePO;
import cn.mabbit.mspc.log.enums.BusinessType;
import cn.mabbit.mspc.log.enums.OperatorType;
import cn.mabbit.mspc.log.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * <h2>日志表</h2>
 *
 * @Date 2023-12-14 9:14
 */
@Getter
@Setter
@ToString
public class SysLog extends BasePO<Long>
{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 业务状态
     */
    private Status status;

    /**
     * 业务处理时间
     */
    private String castTime;

    /**
     * 业务模块
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求地址
     */
    private String uri;

    /**
     * 请求 ip
     */
    private String ip;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 响应结果
     */
    private String result;

    /**
     * 操作人类型
     */
    private OperatorType operatorType;

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 业务失败时抛出的异常类型
     */
    @JsonIgnore
    private String errorType;

    /**
     * 异常编号
     */
    @JsonIgnore
    private ServiceCode errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;
}