package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.transfer.SignUpForm;

import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface AccountService {
    User signUp(SignUpForm user);

    void activate(String activationKey);

    Optional<User> getUser(String email);

    void setResetKey(User user);

    void resetPassword(String resetKey, String password);
}
