package com.demo.ftl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("title", "nekochips");
        map.addAttribute("host", "localhost");
        map.addAttribute("homeIndex", "HOME");
        return "index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String toTest() {
        return "hello world";
    }
}
