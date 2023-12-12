package cn.mabbit.mspc.core.consts;

import cn.mabbit.mspc.core.web.entity.ServiceCode;

/**
 * <h2>业务状态码池</h2>
 * <p>使用时编写接口继承此接口，可扩展该池</p>
 *
 * @author 一只枫兔
 * @Date 2023/10/10 10:36
 */
public interface ServiceCodePool
{
    ServiceCode OK = ServiceCode.newCode("0", "请求成功");
    ServiceCode ERR_UNKNOWN = ServiceCode.newCode("1", "未知错误");
    ServiceCode ERR_SYSTEM = ServiceCode.newCode("2", "系统错误");
    ServiceCode ERR_INVALID = ServiceCode.newCode("3", "非法参数");
    ServiceCode ERR_NOT_FOUND = ServiceCode.newCode("4", "无法获取请求结果");
}