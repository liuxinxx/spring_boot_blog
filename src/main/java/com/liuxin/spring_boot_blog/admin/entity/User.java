package com.liuxin.spring_boot_blog.admin.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Table(name = "tb_user")
@Data
public class User {
    @Id
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String salt;
    private String avatar;
    private String introduce;
    private String remark;
    //    创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //    更新时间
    @Column(name = "updated_time")
    private Date updatedTime;
    @Transient
    private String checkPass;
}
