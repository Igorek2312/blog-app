package com.github.igorek2312.blog.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Igor Rybak
 */
@Controller
public class IndexController {
    @GetMapping({"/"})
    public String index(
            @RequestParam(name = "login", required = false) String login,
            RedirectAttributes redirectAttributes
    ) {
        if (login != null)
            redirectAttributes.addAttribute("login","true");

        return "redirect:/posts";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
