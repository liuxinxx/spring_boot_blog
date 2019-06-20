package com.liuxin.spring_boot_blog.admin.controller;

import com.liuxin.spring_boot_blog.admin.annotation.Log;
import com.liuxin.spring_boot_blog.admin.dto.QueryPage;
import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import com.liuxin.spring_boot_blog.admin.entity.LoginLog;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.service.LoginLogService;
import com.liuxin.spring_boot_blog.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuxin
 * @date 2019/6/20 18:04
 * @desc
 */
@RestController
@RequestMapping("/loginlog")
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/list")
    public ResponseCode findByPage(QueryPage queryPage, LoginLog loginLog) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> loginLogService.findByPage(loginLog)));
    }

    @Log("删除登录日志")
    @PostMapping("/delete")
    @RequiresPermissions("loginlog:list")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            loginLogService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}