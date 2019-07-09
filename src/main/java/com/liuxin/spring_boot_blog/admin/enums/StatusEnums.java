package com.liuxin.spring_boot_blog.admin.enums;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum StatusEnums {
    SUCCESS(200, "操作成功", 1),

    ACCOUNT_UNKNOWN(500, "账户不存在", 0),

    ACCOUNT_ERROR(500, "用户名或密码错误", 0),

    SYSTEM_ERROR(500, "系统错误", 0),

    PARAM_ERROR(400, "参数错误", 0),

    PARAM_REPEAT(400, "参数已存在", 0),

    OTHER(-100, "其他错误", 0),

    NOT_FOUNT(200, "没有找到", 0),

    NOT_PERMISSION(403,"没有权限,请登录!",1),

    PERMISSION_DENIED(403, "没有权限", 0);

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String info;

    @Getter
    @Setter
    private int status;

    StatusEnums(int code, String info, int status) {
        this.code = code;
        this.info = info;
        this.status = status;
    }
}
