package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.User;

/**
 * @author Igor Rybak
 */
public interface EmailService {
    void sendResetPasswordLetter(String origin, User user);

    void sendActivationLetter(String origin, User user);
}
