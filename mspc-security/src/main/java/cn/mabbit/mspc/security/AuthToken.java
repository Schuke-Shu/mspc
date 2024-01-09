package cn.mabbit.mspc.security;

import cn.jruyi.core.lang.Assert;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serial;
import java.util.Collection;

/**
 * <h2>认证令牌</h2>
 *
 * @Date 2023-12-25 13:20
 */
public class AuthToken extends AbstractAuthenticationToken
{
    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 登录时存放 token，其他请求用于存放解析 token 后获取的当事人
     */
    private final Object principal;

    public AuthToken(Object principal, Collection<? extends GrantedAuthority> authorities)
    {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // setAuthenticated() 已经被重写为传入 true 值会报错，所以这里使用父类的方法
    }

    @Override
    public Object getPrincipal()
    {
        return this.principal;
    }

    @Override
    public Object getCredentials()
    {
        throw new RuntimeException();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException
    {
        Assert.isFalse(isAuthenticated, () -> "无法将此令牌设置为已认证，请使用构造函数");
        super.setAuthenticated(false);
    }

    /**
     * 若已经验证过，返回父类的 toString()，否则只返回 token
     */
    @Override
    public String toString()
    {
        return isAuthenticated() ? super.toString() : "Token: " + principal;
    }
}