package com.github.igorek2312.blog.app.config.security;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Igor Rybak
 */
@Service
public class SocialConnectionSignUpAdapter implements ConnectionSignUp {
    @Autowired
    private AccountService accountService;

    @Override
    public String execute(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        String username = key.getProviderId() + ":" + key.getProviderUserId();

        User user = accountService.findByUsername(username)
                .orElseGet(() -> createUser(connection, username));

        return user.getUsername();
    }

    private User createUser(Connection<?> connection, String username) {
        String displayName = null;
        if (connection.getApi() instanceof TwitterTemplate) {
            TwitterTemplate api = (TwitterTemplate) connection.getApi();
            displayName = api.userOperations().getUserProfile().getName();
        } else {
            displayName = connection.getDisplayName();
        }

        return accountService.createUser(
                username,
                connection.getImageUrl(),
                displayName
        );
    }
}
