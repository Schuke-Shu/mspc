package cn.mabbit.test.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static cn.jruyi.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2></h2>
 *
 * @Date 2023-11-29 16:18
 */
@Getter
@Setter
public class TestVO
{
    String test;
    @JsonFormat(pattern = DATETIME_PATTERN)
    LocalDateTime createTime;
}