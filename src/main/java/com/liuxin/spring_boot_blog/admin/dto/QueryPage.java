package com.liuxin.spring_boot_blog.admin.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxin
 * @date 2019/6/20 18:05
 * @desc
 */
@Data
public class QueryPage implements Serializable {

    private int pageCode;
    private int pageSize;
}