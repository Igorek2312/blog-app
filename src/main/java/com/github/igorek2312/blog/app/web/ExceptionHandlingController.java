package com.github.igorek2312.blog.app.web;

import com.github.igorek2312.blog.app.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Igor Rybak
 */
@ControllerAdvice
public class ExceptionHandlingController {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(CustomException.class)
    public String handle(CustomException e, Model model) {
        String message = messageSource.getMessage(
                e.getMessageCode(),
                e.getMessageArgs(),
                "",
                LocaleContextHolder.getLocale()
        );

        model.addAttribute("status", e.getHttpStatus().value());
        model.addAttribute("reasonPhrase", e.getHttpStatus().getReasonPhrase());
        model.addAttribute("message", message);
        return "error-page";
    }

}
