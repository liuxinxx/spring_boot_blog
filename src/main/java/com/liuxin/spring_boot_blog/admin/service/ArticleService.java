package com.liuxin.spring_boot_blog.admin.service;

import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.common.service.BaseService;

/**
 * @author liuxin
 * @date 2019/6/24 14:36
 * @desc
 */
public interface ArticleService extends BaseService<Article> {
    Article findById(Long id);
}
