package com.liuxin.spring_boot_blog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.admin.entity.User;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.mapper.ArticleMapper;
import com.liuxin.spring_boot_blog.admin.service.ArticleService;
import com.liuxin.spring_boot_blog.common.service.impl.BaseServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int delete(Long id) {
        try {
            int count = super.delete(id);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Article findById(Long id) {
        Article article = new Article();
        if (!id.equals(null) && id != 0) {
            article = articleMapper.selectByPrimaryKey(id);
        }
        return article;
    }

    @Override
    @Transactional
    public void save(Article article) {
        try {
            if (article.getState() == "1") {
                article.setPublishTime(new Date());
            }
            article.setAuthor(((User) SecurityUtils.getSubject().getPrincipal()).getUsername());
            article.setEditTime(new Date());
            article.setCreateTime(new Date());
            articleMapper.insert(article);
            article.setId(articleMapper.getLastId());
//            updateArticleCategoryTags(article);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    public List<Article> all() {
        Example example = new Example(Article.class);
        example.setOrderByClause("`id` desc");
        return articleMapper.selectByExampleAndRowBounds(example, new RowBounds(0, 8));
    }

    @Override
    public Map<String, Object> findByPageForSite(int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<Article> page = articleMapper.findByPageForSite();
        List<Article> articleList = page.getResult();
//        findInit(articleList);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("data", articleList);
        return map;
    }
}
