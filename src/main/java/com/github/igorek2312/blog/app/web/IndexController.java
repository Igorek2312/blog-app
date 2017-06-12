package com.github.igorek2312.blog.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Igor Rybak
 */
@Controller
public class IndexController {
    @GetMapping({"/","/login"})
    public String index() {
        return "user/login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
