package com.liuxin.spring_boot_blog.admin.mapper;

import com.github.pagehelper.Page;
import com.liuxin.spring_boot_blog.admin.config.MyMapper;
import com.liuxin.spring_boot_blog.admin.entity.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuxin
 * @date 2019/6/24 14:40
 * @desc
 */
public interface ArticleMapper extends MyMapper<Article> {

    @Select("SELECT id FROM tb_article ORDER BY id DESC LIMIT 1")
    long getLastId();

    @Select("select * from tb_article;")
    List<Article> all();

    @Select("select * from tb_article where state = '1' order by id desc")
    Page<Article> findByPageForSite();

}
