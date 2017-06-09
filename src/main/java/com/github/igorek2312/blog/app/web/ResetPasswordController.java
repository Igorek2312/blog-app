package com.github.igorek2312.blog.app.web;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.services.AccountService;
import com.github.igorek2312.blog.app.services.EmailService;
import com.github.igorek2312.blog.app.transfer.ResetPasswordForm;
import com.github.igorek2312.blog.app.transfer.SendResetPasswordLetterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Igor Rybak
 */
@Controller
public class ResetPasswordController {
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
        return "send-reset-password-letter";
    }

    @PostMapping("/send-reset-password-letter")
    public String sendResetPasswordLetter(
            @Validated @ModelAttribute("sendResetPasswordLetterForm") SendResetPasswordLetterForm form,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            return "send-reset-password-letter";
        }

        Optional<User> userOptional = accountService.getUser(form.getEmail());
        if (userOptional.isPresent()) {
            String originUrl = request.getHeader("Origin");
            User user = userOptional.get();
            accountService.setResetKey(user);
            emailService.sendResetPasswordLetter(originUrl, user);
            return "redirect:/send-reset-password-letter?letter_sent";
        }

        bindingResult.rejectValue("email", "NoUserWithSuchEmail");
        return "send-reset-password-letter";
    }

    @GetMapping("/reset-password")
    public String getResetPassword() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @Validated @ModelAttribute("resetPasswordForm") ResetPasswordForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "reset-password";
        }

        accountService.resetPassword(
                form.getResetKey(),
                form.getPassword()
        );

        return "redirect:/login?password_reset";
    }
}
