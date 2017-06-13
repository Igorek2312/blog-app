package com.github.igorek2312.blog.app.transfer.user;

import com.github.igorek2312.blog.app.services.validation.UniqueEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Igor Rybak
 */
public class UpdateEmailForm {
    @Email
    @NotBlank
    @UniqueEmail
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
