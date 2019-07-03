package com.liuxin.spring_boot_blog.common.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuxin.spring_boot_blog.admin.dto.QueryPage;
import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import com.liuxin.spring_boot_blog.admin.entity.User;
import com.liuxin.spring_boot_blog.admin.enums.StatusEnums;
import com.liuxin.spring_boot_blog.admin.utils.WebUtilsPro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


/**
 * 控制器基类WebUtilsPro
 */
public class BaseController {

    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected User getCurrentUser() {
        return (User) getSubject().getPrincipal();
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    /**
     * 登录认证异常
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
        if (WebUtilsPro.isAjaxRequest(request)) {
            writeJson(new ResponseCode(StatusEnums.PERMISSION_DENIED), response);
            return null;
        } else {
            return "redirect:/system/login";
        }
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (WebUtilsPro.isAjaxRequest(request)) {
            writeJson(new ResponseCode(StatusEnums.PERMISSION_DENIED), response);
            return null;
        } else {
            return "redirect:/403";
        }
    }

    /**
     * 输出JSON
     *
     * @param response
     * @author SHANHY
     * @create 2017年4月4日
     */
    private void writeJson(ResponseCode map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter();
            out = response.getWriter();
            out.write(JSON.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    private Map<String, Object> getData(PageInfo<?> pageInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("rows", pageInfo.getList());
        data.put("total", pageInfo.getTotal());
        return data;
    }

    /**
     * Supplier是JDK8新特性
     * 特点：只有返回值，没有输入参数
     * 如：Supplier<User> user = User::new;
     * 此时并没有构造User对象，当调用`user.get()`方法才获取一个新的User构造对象
     * <p>
     * QueryPage 是封装分页条件的entity，如果没有指定默认查询所有
     */
    public Map<String, Object> selectByPageNumSize(QueryPage page, Supplier<?> s) {
        PageHelper.startPage(page.getPageCode(), page.getPageSize());
        PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
        PageHelper.clearPage();
        return getData(pageInfo);
    }

    public Map<String, Object> getToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", getSession().getId());
        return map;
    }

}
