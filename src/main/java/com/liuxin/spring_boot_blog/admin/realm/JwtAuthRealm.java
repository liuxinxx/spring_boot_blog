package com.liuxin.spring_boot_blog.admin.realm;

import com.liuxin.spring_boot_blog.admin.dto.JwtToken;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.service.UserService;
import com.liuxin.spring_boot_blog.admin.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxin
 * @date 2019/7/5 15:50
 * @desc
 */
@Slf4j
@Component
public class JwtAuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "JwtAuthRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        // 只支持JwtToken令牌类型
        return authenticationToken instanceof JwtToken;
    }

    /**
     * 权限校验
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 身份校验
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("----------------  JWT 身份认证  ----------------");
        String token = (String) authenticationToken.getCredentials();
        String id = JWTUtil.decode(token);  // 解密Token
        if (id == null) {
            // Token解密失败，抛出异常
            log.warn("Token解密失败,抛出异常!");
            throw new AuthenticationException("token认证失败!");
        }
        log.info("============================");
        // Token解密成功，返回SimpleAuthenticationInfo对象
        return new SimpleAuthenticationInfo(token, token, "JwtAuthRealm");
    }
}
