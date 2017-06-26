package com.github.igorek2312.blog.app.service.validation;

import com.github.igorek2312.blog.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Igor Rybak
 */
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserRepository userRepository;

    public void initialize(UniqueUsername constraint) {
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(username);
    }
}
