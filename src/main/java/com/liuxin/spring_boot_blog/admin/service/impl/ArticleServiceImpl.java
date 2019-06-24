package com.liuxin.spring_boot_blog.admin.service.impl;

import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.admin.mapper.ArticleMapper;
import com.liuxin.spring_boot_blog.admin.service.ArticleService;
import com.liuxin.spring_boot_blog.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxin
 * @date 2019/6/24 14:36
 * @desc
 */
@Service
@SuppressWarnings("all")
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article findById(Long id) {
        Article article = new Article();
        //  判断id是否为空
        if (id.equals(null) && id != 0) {
            article = articleMapper.selectByPrimaryKey(id);
        }
        return article;
    }

}
