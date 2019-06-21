package com.liuxin.spring_boot_blog.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liuxin
 * @date 2019/6/21 14:07
 * @desc
 */
@Controller
public class SiteController {

    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        return "site/index";
    }


}
