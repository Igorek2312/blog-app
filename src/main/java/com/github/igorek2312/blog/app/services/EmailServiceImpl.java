package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.User;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Rybak
 */
@Component
public class EmailServiceImpl implements EmailService {
    private JavaMailSender mailSender;
    private Environment environment;
    private Configuration freeMarkerConfigurer;

    @Autowired
    public EmailServiceImpl(
            JavaMailSender mailSender,
            Environment environment,
            @Qualifier("freeMarkerConfiguration") Configuration freeMarkerConfigurer
    ) {
        this.mailSender = mailSender;
        this.environment = environment;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    @Override
    public void sendResetPasswordLetter(String originUrl, User user) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(user.getEmail());
            message.setFrom(environment.getProperty("email.username"));

            Map<String, String> model = new HashMap<>();
            model.put("resetPasswordUrl", originUrl + "/reset-password?key=" + user.getResetKey());

            String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfigurer.getTemplate("email-reset-password.ftl", "UTF-8"),
                    model
            );

            message.setText(text, true);
        };

        mailSender.send(preparator);
    }

    @Override
    public void sendActivationLetter(String originUrl, User user) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(user.getEmail());
            message.setFrom(environment.getProperty("EMAIL_USERNAME"));

            Map<String, String> model = new HashMap<>();
            model.put("activationUrl", originUrl + "/activate?key=" + user.getActivationKey());

            String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfigurer.getTemplate("email-activate-user.ftl", "UTF-8"),
                    model
            );

            message.setText(text, true);
        };

        mailSender.send(preparator);
    }


}
