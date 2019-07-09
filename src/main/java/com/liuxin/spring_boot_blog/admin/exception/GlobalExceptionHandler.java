package com.liuxin.spring_boot_blog.admin.exception;

import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuxin
 * @date 2019/7/5 18:15
 * @desc
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 捕捉shiro的异常
    @ExceptionHandler(value = ShiroException.class)
    public ResponseCode handle401() {
        return ResponseCode.notPermission();
    }

    // 捕捉其他所有异常
    @ExceptionHandler(value = Exception.class)
    public ResponseCode exception(HttpServletRequest request, Throwable ex) {
        return ResponseCode.notPermission(ex.getMessage());
    }

    // 自定义异常
    @ExceptionHandler(value = GlobalException.class)
    public ResponseCode globalException(GlobalException e) {
        log.error("捕获 GlobalException 异常");
        return ResponseCode.notPermission(e.getMsg());
    }

}
