package com.liuxin.spring_boot_blog.admin.controller;

import com.alibaba.fastjson.JSON;
import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.admin.entity.Tags;
import com.liuxin.spring_boot_blog.admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxin
 * @date 2019/6/24 14:37
 * @desc
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id, Model model) {
        Article article = articleService.findById(id);
        if (article.getId() != 0) {
            List<String> tags = new ArrayList<>();
//            List<Tags> tagList = articleTagService.findByArticleId(article.getId());
//            tagList.forEach(t -> {
//                tags.add(t.getName());
//            });
            article.setTags(JSON.toJSONString(tags));
            return ResponseCode.success(article);
        } else {
            return ResponseCode.error();
        }
    }

}
