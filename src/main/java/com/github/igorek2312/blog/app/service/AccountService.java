package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.model.User;

import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface AccountService {
    Optional<User> findByUsername(String username);

    User createUser(String username, String imageUrl, String displayName);

    void signUp(User user);

    void activate(String activationKey);

    Optional<User> getUser(String email);

    void setResetKey(User user);

    void resetPassword(String resetKey, String password);

}
