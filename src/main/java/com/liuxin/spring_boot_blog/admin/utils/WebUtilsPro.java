package com.liuxin.spring_boot_blog.admin.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuxin
 * @date 2019/7/3 13:46
 * @desc
 */
public class WebUtilsPro {
    /**
     * 判断是否是Ajax 请求
     *
     * @param request
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
