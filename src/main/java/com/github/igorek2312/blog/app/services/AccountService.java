package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.User;

import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface AccountService {
    void signUp(User user);

    void activate(String activationKey);

    Optional<User> getUser(String email);

    void setResetKey(User user);

    void resetPassword(String resetKey, String password);
}
