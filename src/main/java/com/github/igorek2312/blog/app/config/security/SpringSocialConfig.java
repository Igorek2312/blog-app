package com.github.igorek2312.blog.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.google.connect.GoogleConnectionFactory;

/**
 * @author Igor Rybak
 */
@Configuration
public class SpringSocialConfig {
    @Value("${spring.social.google.appId}")
    private String appId;

    @Value("${spring.social.google.appSecret}")
    private String appSecret;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionSignUp socialConnectionSignUpAdapter;

    @Autowired
    private SignInAdapter socialSignInAdapter;

    @Autowired
    public void setConnectionFactoryRegistry(ConnectionFactoryRegistry registry) {
        registry.addConnectionFactory(new GoogleConnectionFactory(appId, appSecret));
    }

    @Bean
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(socialConnectionSignUpAdapter);

        ProviderSignInController providerSignInController = new ProviderSignInController(
                connectionFactoryLocator,
                usersConnectionRepository,
                socialSignInAdapter
        );

        providerSignInController.setPostSignInUrl("/my-profile");
        return providerSignInController;
    }
}
