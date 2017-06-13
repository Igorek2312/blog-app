package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.repositories.UserRepository;
import com.github.igorek2312.blog.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void update(String firstName, String lastName) {
        String username = SecurityUtils.getCurrentUsername();
        userRepository.save(
                username,
                firstName,
                lastName
        );
    }

    @Transactional
    @Override
    public void updateEmail(String email) {
        String username = SecurityUtils.getCurrentUsername();
        userRepository.save(
                username,
                email
        );
    }
}
