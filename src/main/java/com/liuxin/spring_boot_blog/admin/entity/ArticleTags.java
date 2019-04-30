package com.liuxin.spring_boot_blog.admin.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "tag_article")
public class ArticleTags {
    @Id
    private Long id;
    @Column(name = "tag_id")
    @NotNull
    private Long tagId;
    @Column(name = "article_id")
    @NotNull
    private Long articleId;
    //    创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //    更新时间
    @Column(name = "updated_time")
    private Date updatedTime;
    public ArticleTags() {
    }

    public ArticleTags(Long articleId, Long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
