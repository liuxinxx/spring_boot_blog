package com.liuxin.spring_boot_blog.admin.filter;

import com.liuxin.spring_boot_blog.admin.dto.JwtToken;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author liuxin
 * @date 2019/7/5 17:45
 * @desc
 */
@Slf4j
public class JWTShiroFilter extends BasicHttpAuthenticationFilter {

    // 判断Token头是否为空
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        return req.getHeader("Authorization") != null;
    }

    /**
     * 首先调用的一个方法，在该方法内进行主要的认证逻辑处理，如判断Token头是否为空，解密Token等
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws GlobalException {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);  // 执行登录,如果登录失败抛出异常
            } catch (Exception e) {
                //token 错误
                throw new GlobalException(e.getMessage());
            }
            return true;
        }
        // Token为空
        throw new GlobalException("Token is Null");
//        return false
//        return abort401(request, response);
    }

    /**
     * 调用Realm执行登录，并返回登录认证的结果
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws AuthenticationException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");

        JwtToken token = new JwtToken(authorization);
        // 当调用Subject对象的login方法时，将会交给我们自己实现的MyRealm来处理登录的认证逻辑
        Subject subject = getSubject(request, response);
        subject.login(token);
        return true;  // 登录成功
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/unauthorized/" + message);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}