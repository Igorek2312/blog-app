package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.repository.UserRepository;
import com.github.igorek2312.blog.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Igor Rybak
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("No such user"));
    }

    @Override
    public User getById(int userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public Optional<String> getImageUrl(String username) {
        return userRepository.findImageUrlByUserName(username);
    }

    @Transactional
    @Override
    public void update(String firstName, String lastName) {
        String username = SecurityUtils.getCurrentUsername();
        userRepository.update(
                username,
                firstName,
                lastName
        );
    }

    @Transactional
    @Override
    public void updateEmail(String email) {
        String username = SecurityUtils.getCurrentUsername();
        userRepository.update(
                username,
                email
        );
    }

    @Transactional
    @Override
    public void changeUserImageUrl(String url, String username) {
        userRepository.updateImageUrl(url, username);
    }
}
