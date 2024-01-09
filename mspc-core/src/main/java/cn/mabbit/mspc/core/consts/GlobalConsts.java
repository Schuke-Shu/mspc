package cn.mabbit.mspc.core.consts;

/**
 * <h2>全局常量接口</h2>
 *
 * @Date 2023-10-09 18:15
 */
public interface GlobalConsts
{
    /**
     * 日志文件夹名称
     */
    String LOG_PATH_DIR = "log";
    /**
     * 存储异常对象文件的文件夹地址
     */
    String ERROR_FILE_DIR = "log/error";
    /**
     * 异常文件名
     */
    String ERROR_FILENAME_PATTERN = "yyyy-MM-dd_HH.mm.ss.SSS";
    /**
     * 属性异常模板
     */
    String PROPERTIES_ERROR_PATTERN = "property: [%s - %s] is illegal";
}