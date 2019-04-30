package com.liuxin.spring_boot_blog.common.controller;

import com.liuxin.spring_boot_blog.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * 控制器基类
 */
public class BaseController {

    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前用户
     * @return
     */
    protected User getCurrentUser() {
        return (User) getSubject().getPrincipal();
    }
}
