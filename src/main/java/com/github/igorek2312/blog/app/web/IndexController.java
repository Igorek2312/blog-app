package com.github.igorek2312.blog.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * @author Igor Rybak
 */
@Controller
public class IndexController {
    @GetMapping({"/"})
    public String index(
            @RequestParam Map<String, String> requestParams,
            RedirectAttributes redirectAttributes
    ) {
        requestParams.keySet()
                .forEach(param -> redirectAttributes.addAttribute(param, "true"));

        return "redirect:/posts";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
