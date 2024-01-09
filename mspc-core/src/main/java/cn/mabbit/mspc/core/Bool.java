package cn.mabbit.mspc.core;

/**
 * <h2>布尔值枚举</h2>
 *
 * <p>
 * 定义：
 *     <ul>若为数值，大于0的值为 {@code true}，小于等于0的值为 {@code false}</ul>
 *     <ul>若为对象，检查对象是否为 {@code null}</ul>
 * </p>
 *
 * @author 一只枫兔
 * @Date 2023/10/11 9:00
 */
public enum Bool
{
    FALSE("false"),
    TRUE("true"),
    ;

    private final String text;

    private static final int TRUE_NUM = 1;
    private static final int FALSE_NUM = 0;

    Bool(String text)
    {
        this.text = text;
    }

    /**
     * @return 代表布尔值的数值
     */
    public int digit()
    {
        return ordinal();
    }

    /**
     * @return 基本类型布尔值
     */
    public boolean value()
    {
        return this == TRUE;
    }

    /**
     * 转换 {@link Number}
     *
     * <table>
     *     <tr><th style="width: 100px">参数值</th><th style="width: 100px">返回值</th></tr>
     *     <tr><td>{@code null}</td><td>{@link #FALSE}</td></tr>
     *     <tr><td>&gt; 0</td><td>{@link #TRUE}</td></tr>
     *     <tr><td>&lt;= 0</td><td>{@link #FALSE}</td></tr>
     * </table>
     *
     * @param n 数值
     * @return {@link Bool}
     */
    public static Bool of(Number n)
    {
        return n == null || n.doubleValue() < TRUE_NUM ? FALSE : TRUE;
    }

    /**
     * 转换 {@link Boolean}
     *
     * <table>
     *     <tr><th style="width: 100px">参数值</th><th style="width: 100px">返回值</th></tr>
     *     <tr><td>{@code null}</td><td>{@link #FALSE}</td></tr>
     *     <tr><td>{@link Boolean#TRUE}</td><td>{@link #TRUE}</td></tr>
     *     <tr><td>{@link Boolean#FALSE}</td><td>{@link #FALSE}</td></tr>
     * </table>
     *
     * @param b 布尔值
     * @return {@link Bool}
     */
    public static Bool of(Boolean b)
    {
        return Boolean.TRUE.equals(b) ? TRUE : FALSE;
    }

    /**
     * 转换 {@link Object}
     *
     * <table>
     *     <tr><th style="width: 100px">参数值</th><th style="width: 100px">返回值</th></tr>
     *     <tr><td>{@code null}</td><td>{@link #FALSE}</td></tr>
     *     <tr><td>{@link Number}</td><td>{@link #of(Number)}</td></tr>
     *     <tr><td>{@link Boolean}</td><td>{@link #of(Boolean)}</td></tr>
     *     <tr><td>{@code Other}</td><td>{@link #TRUE}（{@code obj} 不为 {@code null}）</td></tr>
     * </table>
     *
     * @param obj 对象
     * @return {@link Bool}
     */
    public static Bool of(Object obj)
    {
        return
                switch (obj)
                {
                    case null -> FALSE;
                    case Number n -> of(n);
                    case Boolean b -> of(b);
                    default -> TRUE;
                };
    }

    @Override
    public String toString()
    {
        return text;
    }
}