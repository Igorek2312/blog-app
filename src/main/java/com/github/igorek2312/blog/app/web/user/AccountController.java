package com.github.igorek2312.blog.app.web.user;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.service.AccountService;
import com.github.igorek2312.blog.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Rybak
 */
@Controller
public class AccountController {
    private final AccountService accountService;
    private final EmailService emailService;

    @Autowired
    public AccountController(AccountService accountService, EmailService emailService) {
        this.accountService = accountService;
        this.emailService = emailService;
    }

    @ModelAttribute("user")
    public User signUpForm() {
        return new User();
    }

    @GetMapping("/sign-up")
    public String getSignUp() {
        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @Validated @ModelAttribute("user") User user,
            BindingResult result,
            HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return "user/sign-up";
        }
        accountService.signUp(user);
        String originUrl = request.getHeader("Origin");
        emailService.sendActivationLetter(originUrl, user);
        return "redirect:login?letter_sent";
    }

    @GetMapping("/activate")
    public String activate(@RequestParam("key") String activationKey) {
        accountService.activate(activationKey);
        return "redirect:login?signed_up";
    }


}