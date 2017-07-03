package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.error.NotFoundException;
import com.github.igorek2312.blog.app.model.Role;
import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.repository.RoleRepository;
import com.github.igorek2312.blog.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private final String defaultImage;

    @Autowired
    public AccountServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            @Value("default-profile-image-url") String defaultImage
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultImage = defaultImage;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(String username, String imageUrl, String displayName) {
        User user = new User();
        user.setUsername(username);

        int spaceIndex = displayName.indexOf(" ");
        if (spaceIndex != -1) {
            user.setFirstName(displayName.substring(0, spaceIndex));
            user.setLastName(displayName.substring(spaceIndex + 1, displayName.length()));
        }
        else {
            user.setFirstName(displayName);
            user.setLastName("");
        }

        user.setPassword(UUID.randomUUID().toString());
        user.encodePassword(passwordEncoder::encode);

        user.setActivated(true);

        Role role = roleRepository.findByName("ROLE_USER");
        user.getRoles().add(role);

        user.setImageUrl(Optional.ofNullable(imageUrl).orElse(defaultImage));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void signUp(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.encodePassword(passwordEncoder::encode);
        user.setActivationKey(UUID.randomUUID().toString());
        user.getRoles().add(role);
        user.setImageUrl(defaultImage);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void activate(String activationKey) {
        User user = userRepository.findByActivationKey(activationKey)
                .orElseThrow(() -> new NotFoundException("error.no.user.with.such.activation.key"));
        user.setActivated(true);
        userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void setResetKey(User user) {
        user.setResetKey(UUID.randomUUID().toString());
        userRepository.saveAndFlush(user);
    }

    @Override
    public void resetPassword(String resetKey, String password) {
        User user = userRepository.findByResetKey(resetKey)
                .orElseThrow(() -> new NotFoundException("error.no.user.with.such.reset.key"));
        user.setPasswordHash(password);
        user.encodePassword(passwordEncoder::encode);
        userRepository.saveAndFlush(user);
    }
}
