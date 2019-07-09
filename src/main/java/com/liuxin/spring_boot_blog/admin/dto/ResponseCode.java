package com.liuxin.spring_boot_blog.admin.dto;


import com.liuxin.spring_boot_blog.admin.enums.StatusEnums;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCode {

    private Integer code;
    private Integer status;
    private String msg;
    private Object data;

    public ResponseCode(StatusEnums enums) {
        this.code = enums.getCode();
        this.msg = enums.getInfo();
        this.status = enums.getStatus();
    }

    public ResponseCode(StatusEnums enums, Object data) {
        this.code = enums.getCode();
        this.msg = enums.getInfo();
        this.status = enums.getStatus();
        this.data = data;
    }

    public ResponseCode(Integer status, Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    public static ResponseCode success() {
        return new ResponseCode(StatusEnums.SUCCESS);
    }


    public static ResponseCode success(Object data) {
        return new ResponseCode(StatusEnums.SUCCESS, data);
    }

    public static ResponseCode error(Object data) {
        return new ResponseCode(StatusEnums.SUCCESS, data);
    }

    public static ResponseCode error() {
        return new ResponseCode(StatusEnums.SYSTEM_ERROR);
    }

    public static ResponseCode error(String msg) {
        return new ResponseCode(StatusEnums.SUCCESS, msg);
    }

    public static ResponseCode notFound() {
        return new ResponseCode(StatusEnums.NOT_FOUNT);
    }

    public static ResponseCode notPermission() {
        return new ResponseCode(StatusEnums.NOT_PERMISSION);
    }
    public static ResponseCode notPermission(String msg) {
        return new ResponseCode(StatusEnums.NOT_PERMISSION,msg);
    }
    public static ResponseCode notFound(Object data) {
        return new ResponseCode(StatusEnums.NOT_FOUNT, data);
    }
}
