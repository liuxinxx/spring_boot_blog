package com.liuxin.spring_boot_blog.admin.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Table(name = "tb_tags")
public class Tags {
    @Id
    private Long id;
    //  标签名称
    @NotNull
    private String name;

    //    创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //    更新时间
    @Column(name = "updated_time")
    private Date updatedTime;
    @Transient
    private Long count;

    public Tags() {
    }

    public Tags(String name) {
        this.name = name;
    }
}
