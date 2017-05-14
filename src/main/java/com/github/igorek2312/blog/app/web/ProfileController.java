package com.github.igorek2312.blog.app.web;

import com.github.igorek2312.blog.app.utils.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Igor Rybak
 */
@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("username", SecurityUtils.getCurrentUserLogin());
        return "profile";
    }

}
