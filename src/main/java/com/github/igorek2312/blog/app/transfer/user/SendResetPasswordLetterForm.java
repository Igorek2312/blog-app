package com.github.igorek2312.blog.app.transfer.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Igor Rybak
 */
public class SendResetPasswordLetterForm {
    @Email
    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
