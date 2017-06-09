package com.github.igorek2312.blog.app.web;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.services.AccountService;
import com.github.igorek2312.blog.app.services.EmailService;
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
    private AccountService accountService;
    private EmailService emailService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @ModelAttribute("user")
    public User signUpForm() {
        return new User();
    }

    @GetMapping("/sign-up")
    public String getSignUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @Validated @ModelAttribute("user") User user,
            BindingResult result,
            HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return "sign-up";
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