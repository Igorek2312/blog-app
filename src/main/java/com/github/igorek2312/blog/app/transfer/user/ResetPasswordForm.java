package com.github.igorek2312.blog.app.transfer.user;

import com.github.igorek2312.blog.app.services.validation.ConfirmPasswordConstraint;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Igor Rybak
 */
@ConfirmPasswordConstraint
public class ResetPasswordForm implements ConfirmPassword {
    @Length(min = 4, max = 30)
    @NotBlank
    private String password;

    private String confirmPassword;

    @Length(min = 36, max = 36)
    private String resetKey;

    @Override
    public boolean isConfirmedPasswordValid() {
        return password.equals(confirmPassword);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }
}
