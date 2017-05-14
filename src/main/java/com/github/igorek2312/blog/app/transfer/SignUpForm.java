package com.github.igorek2312.blog.app.transfer;

import com.github.igorek2312.blog.app.reference.Constants;
import com.github.igorek2312.blog.app.services.validation.ConfirmPasswordConstraint;
import com.github.igorek2312.blog.app.services.validation.UniqueEmail;
import com.github.igorek2312.blog.app.services.validation.UniqueUsername;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author Igor Rybak
 */
@ConfirmPasswordConstraint(message = "{ConfirmPasswordConstraint}")
public class SignUpForm implements ConfirmPassword {
    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Length(min = 1, max = 100)
    @NotBlank
    @UniqueUsername(message = "{Unique.user.username}")
    private String username;

    @Length(min = 4, max = 30)
    @NotBlank
    private String password;

    private String confirmPassword;

    @Length(max = 50)
    @NotBlank
    private String firstName;

    @Length(max = 50)
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    @UniqueEmail(message = "{Unique.user.email}")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isConfirmedPasswordValid() {
        return password.equals(confirmPassword);
    }
}
