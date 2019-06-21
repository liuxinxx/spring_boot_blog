package com.liuxin.spring_boot_blog.admin.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User implements Serializable {

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

    @Transient
    private String checkPass;
}
