package com.liuxin.spring_boot_blog.admin.controller;

import com.liuxin.spring_boot_blog.admin.annotation.Log;
import com.liuxin.spring_boot_blog.admin.dto.QueryPage;
import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import com.liuxin.spring_boot_blog.admin.entity.LoginLog;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.service.LoginLogService;
import com.liuxin.spring_boot_blog.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return ResponseCode.success(super.selectByPageNumSize(queryPage,
                () -> loginLogService.findByPage(loginLog)));
    }

    @Log("删除登录日志")
    @DeleteMapping("/delete")
    @RequiresAuthentication
    @RequiresPermissions("user")
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