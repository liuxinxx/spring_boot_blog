package com.liuxin.spring_boot_blog.admin.service;

import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @date 2019/6/24 14:36
 * @desc
 */
public interface ArticleService extends BaseService<Article> {
    Article findById(Long id);

    /**
     * 查询所有，为博客前台服务，仅查询最新的8条数据
     *
     * @return
     */
    List<Article> all();

    /**
     * 分页查询（为博客前端服务）
     *
     * @param pageCode
     * @param pageSize
     * @return
     */
    Map<String, Object> findByPageForSite(int pageCode, int pageSize);
}
