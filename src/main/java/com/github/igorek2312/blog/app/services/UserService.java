package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.User;

/**
 * @author Igor Rybak
 */
public interface UserService {

    User getByUsername(String username);

    User getById(int userId);

    void update(String firstName, String lastName);

    void updateEmail(String email);
}
