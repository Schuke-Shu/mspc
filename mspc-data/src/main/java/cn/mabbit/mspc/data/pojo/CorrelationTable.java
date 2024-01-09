package cn.mabbit.mspc.data.pojo;

/**
 * <h2>关联表接口</h2>
 *
 * @Date 2023-12-07 9:57
 */
public interface CorrelationTable
{
    /**
     * @return 左表主键值
     */
    Long getLid();

    /**
     * @return 右表主键值
     */
    Long getRid();

    /**
     * 设置左表主键值
     *
     * @param id 左表主键值
     */
    void setLid(Long id);

    /**
     * 设置右表主键值
     *
     * @param id 右表主键值
     */
    void setRid(Long id);
}