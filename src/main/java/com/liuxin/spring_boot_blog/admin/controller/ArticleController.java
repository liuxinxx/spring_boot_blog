package com.liuxin.spring_boot_blog.admin.controller;

import com.alibaba.fastjson.JSON;
import com.liuxin.spring_boot_blog.admin.annotation.Log;
import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import com.liuxin.spring_boot_blog.admin.entity.Article;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @date 2019/6/24 14:37
 * @desc
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/api/article")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @GetMapping("/test")
    public String json() throws GlobalException {
        throw new GlobalException("发生错误2");
    }

    @ApiOperation(value = "获取文章详情", notes = "根据url的id来获取文章")
    @GetMapping(value = "/{id}")
    public ResponseCode findById(@PathVariable("id") Long id) {
        Article article = articleService.findById(id);
        log.info("获取文章", article == null);
        if (article != null && article.getId() != 0) {
            List<String> tags = new ArrayList<>();
            article.setTags(JSON.toJSONString(tags));
            return ResponseCode.success(article);
        } else {
            return ResponseCode.notFound();
        }
    }

    @ApiOperation(value = "文章列表", notes = "获取文章列表")
    @GetMapping(value = "/list")
    public ResponseCode all(Model model) {
        return ResponseCode.success(articleService.all());
    }

    @ApiOperation(value = "删除文章", notes = "根据url的id来指定删除对象")
    @DeleteMapping(value = "/{id}")

    public ResponseCode delete(@PathVariable("id") Long id) {
        try {
            int count = articleService.delete(id);
            Map data = new HashMap<>();
            data.put("count", count);
            if (count == 0) {
                return ResponseCode.notFound(data);
            } else {
                return ResponseCode.success(data);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseCode.error();
        }
    }

    @ApiOperation(value = "创建文章", notes = "根据文章对象创建文章")
    @ApiImplicitParam(name = "article", value = "文章实体", required = true, dataType = "Article")
    @PostMapping
    @Log("新增文章")
    public ResponseCode save(@Validated @RequestBody Article article) {
        try {
            articleService.save(article);
            return ResponseCode.success(article);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

}
