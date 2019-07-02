package com.liuxin.spring_boot_blog.admin.controller;

import com.alibaba.fastjson.JSON;
import com.liuxin.spring_boot_blog.admin.annotation.Log;
import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.admin.entity.Tags;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.service.ArticleService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id, Model model) {
        Article article = articleService.findById(id);
        log.info("获取文章", article == null);
        if (article != null && article.getId() != 0) {
            List<String> tags = new ArrayList<>();
            article.setTags(JSON.toJSONString(tags));
            return ResponseCode.success(article);
        } else {
            return ResponseCode.error("未找到文章");
        }
    }

    @GetMapping(value = "/all")
    public ResponseCode all(Model model) {
        return ResponseCode.success(articleService.all());
    }


    @PostMapping(value = "/save")
    @Log("新增文章")
    public ResponseCode save(@Validated @RequestBody Article article) {
        try {
            articleService.save(article);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

}
