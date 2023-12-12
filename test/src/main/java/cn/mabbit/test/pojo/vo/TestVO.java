package cn.mabbit.test.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static cn.mabbit.mdk4j.core.lang.constant.TimeConsts.DATETIME_PATTERN;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
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