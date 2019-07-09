package com.liuxin.spring_boot_blog.admin.dto;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author liuxin
 * @date 2019/7/5 16:16
 * @desc
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
