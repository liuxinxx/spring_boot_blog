package com.liuxin.spring_boot_blog.admin.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class HttpContextUtil {
  public static HttpServletRequest getHttpServletRequest() {
//        Objects.requireNonNull();  判断对象是否为空，空的时候报空指针异常
//        RequestContextHolder.getRequestAttributes()
//        在spring mvc中，为了随时都能取到当前请求的request对象，
//        可以通过RequestContextHolder的静态方法getRequestAttributes()获取Request相关的变量，
//        如request, response等。

    return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
  }
}
