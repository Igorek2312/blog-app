package com.github.igorek2312.blog.app.config.security;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Collections;
import java.util.List;

/**
 * @author Igor Rybak
 */
@Service
public class SocialSignInAdapter implements SignInAdapter {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String signIn(String s, Connection<?> connection, NativeWebRequest nativeWebRequest) {
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        ConnectionKey key = connection.getKey();
        String username = key.getProviderId() + ":" + key.getProviderUserId();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user with username:" + username));
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                new CustomUserDetails(user),
                null,
                authorities
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return null;
    }
}
