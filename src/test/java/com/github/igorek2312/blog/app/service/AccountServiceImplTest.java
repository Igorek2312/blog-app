package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.repository.RoleRepository;
import com.github.igorek2312.blog.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Igor Rybak
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountServiceImpl(
                userRepository,
                roleRepository,
                passwordEncoder,
                "/default"
        );
    }

    @Test
    public void testCreateUserWithSimpleName() throws Exception {
        when(userRepository.saveAndFlush(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgumentAt(0, User.class));
        User user = accountService.createUser("igorek2312", "https://example.com/photo", "Igor Rybak");
        assertEquals("Igor", user.getFirstName());
        assertEquals("Rybak", user.getLastName());
    }

    @Test
    public void testCreateUserWithOneWordName() throws Exception {
        when(userRepository.saveAndFlush(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgumentAt(0, User.class));
        User user = accountService.createUser("hachKalach", "https://example.com/photo", "HachKalach");
        assertEquals("HachKalach", user.getFirstName());
        assertEquals("", user.getLastName());
    }

    @Test
    public void testCreateUserWithTripleWordName() throws Exception {
        when(userRepository.saveAndFlush(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgumentAt(0, User.class));
        User user = accountService.createUser("hachKalach", "https://example.com/photo", "Honoré de Balzac");
        assertEquals("Honoré", user.getFirstName());
        assertEquals("de Balzac", user.getLastName());
    }
}