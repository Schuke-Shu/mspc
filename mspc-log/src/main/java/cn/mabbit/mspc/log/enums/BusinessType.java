package cn.mabbit.mspc.log.enums;

/**
 * <h2>业务类型</h2>
 *
 * @author 一只枫兔
 * @Date 2023-12-13 15:24
 */
public enum BusinessType
{
    /**
     * 新增
     */
    SAVE,
    /**
     * 删除
     */
    REMOVE,
    /**
     * 编辑
     */
    EDIT,
    /**
     * 授权
     */
    GRANT,
    /**
     * 导出
     */
    EXPORT,
    /**
     * 导入
     */
    IMPORT,
    /**
     * 强退
     */
    FORCE,
    /**
     * 生成代码
     */
    GENCODE,
    /**
     * 清除数据
     */
    CLEAN,
    /**
     * 其他
     */
    OTHER,
}