package cn.mabbit.mspc.core.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * <h2>配置文件错误</h2>
 * <p>配置文件出现错误，例如缺少配置或配置不符合要求</p>
 *
 * @author 一只枫兔
 * @Date 2023/10/09 18:15
 */
@Getter
public class PropertiesException
        extends ProjectException
{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误配置类
     */
    private final Class<?> property;

    /**
     * 错误配置项属性名称
     */
    private final String optional;

    private static final String DETAIL_PATTERN = "property error: [%s - %s]";

    public PropertiesException(Class<?> property, String optional)
    {
        this.property = property;
        this.optional = optional;
        detail = String.format(DETAIL_PATTERN, property.getTypeName(), optional);
    }
}