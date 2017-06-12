package com.github.igorek2312.blog.app.web.user;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.services.AccountService;
import com.github.igorek2312.blog.app.services.EmailService;
import com.github.igorek2312.blog.app.transfer.ResetPasswordForm;
import com.github.igorek2312.blog.app.transfer.SendResetPasswordLetterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Igor Rybak
 */
@Controller
public class ResetPasswordController {
    private final AccountService accountService;
    private final EmailService emailService;


    @Autowired
    public ResetPasswordController(
            AccountService accountService,
            EmailService emailService
    ) {
        this.accountService = accountService;
        this.emailService = emailService;
    }

    @ModelAttribute("sendResetPasswordLetterForm")
    public SendResetPasswordLetterForm sendResetPasswordLetterForm() {
        return new SendResetPasswordLetterForm();
    }

    @ModelAttribute("resetPasswordForm")
    public ResetPasswordForm resetPasswordForm() {
        return new ResetPasswordForm();
    }

    @GetMapping("/send-reset-password-letter")
    public String getSendResetPasswordLetter(
    ) {
        return "user/send-reset-password-letter";
    }

    @PostMapping("/send-reset-password-letter")
    public String sendResetPasswordLetter(
            @Validated @ModelAttribute("sendResetPasswordLetterForm") SendResetPasswordLetterForm form,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            return "user/send-reset-password-letter";
        }

        Optional<User> userOptional = accountService.getUser(form.getEmail());
        if (userOptional.isPresent()) {
            String originUrl = request.getHeader("Origin");
            User user = userOptional.get();
            accountService.setResetKey(user);
            emailService.sendResetPasswordLetter(originUrl, user);
            return "redirect:/send-reset-password-letter?letter_sent";
        }

        bindingResult.rejectValue("email", "NotExists.user.email");
        return "user/send-reset-password-letter";
    }

    @GetMapping("/reset-password")
    public String getResetPassword() {
        return "user/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @Validated @ModelAttribute("resetPasswordForm") ResetPasswordForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "user/reset-password";
        }

        accountService.resetPassword(
                form.getResetKey(),
                form.getPassword()
        );

        return "redirect:/login?password_reset";
    }
}
