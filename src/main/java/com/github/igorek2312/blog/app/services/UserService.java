package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.User;

import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface UserService {

    User getByUsername(String username);

    User getById(int userId);

    Optional<String> getImageUrl(String username);

    void update(String firstName, String lastName);

    void updateEmail(String email);

    void changeUserImageUrl(String url, String username);
}
