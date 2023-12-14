package cn.mabbit.mspc.log;

import cn.mabbit.mspc.data.pojo.BasePO;
import cn.mabbit.mspc.log.enums.BusinessType;
import cn.mabbit.mspc.log.enums.OperatorType;
import cn.mabbit.mspc.log.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.time.LocalDateTime;

import static cn.mabbit.mdk4j.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2>日志表</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-14 9:14
 */
public class SysLog
        extends BasePO<Long>
{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 业务模块
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 操作人类型
     */
    private OperatorType operatorType;

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 业务状态
     */
    private Status status;

    /**
     * 业务失败时抛出的异常类型
     */
    private String errorType;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = DATETIME_PATTERN)
    private LocalDateTime time;
}