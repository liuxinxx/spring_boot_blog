package com.liuxin.spring_boot_blog.site.controller;

import com.liuxin.spring_boot_blog.admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author liuxin
 * @date 2019/6/21 14:07
 * @desc
 */
@Controller
public class SiteController {
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model) {

        initIndex(1, model);
        return "site/index";

    }

    /**
     * 初始化首页数据
     *
     * @param pageCode
     * @param model
     */
    private void initIndex(Integer pageCode, Model model) {
        //分页文章数据
        Map<String, Object> map = articleService.findByPageForSite(pageCode, 6);
        map.put("total", (long) Math.ceil(((Long) map.get("total")).doubleValue() / (double) 6));
        //格式：[{...}, {...}, {...}]
        model.addAttribute("list", map);
        model.addAttribute("pageCode", pageCode);
    }


}
