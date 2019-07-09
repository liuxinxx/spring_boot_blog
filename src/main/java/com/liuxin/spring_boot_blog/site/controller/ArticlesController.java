package com.liuxin.spring_boot_blog.site.controller;

import com.alibaba.fastjson.JSON;
import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.admin.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxin
 * @date 2019/6/24 14:37
 * @desc
 */
@Controller
@SuppressWarnings("all")
@Slf4j
public class ArticlesController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        log.info("获取文章", article == null);
        if (article != null && article.getId() != 0) {
            List<String> tags = new ArrayList<>();
            article.setTags(JSON.toJSONString(tags));
            model.addAttribute("article", article);
            return "site/page/detail";
        } else {
            return "error/404";
        }
    }

}
