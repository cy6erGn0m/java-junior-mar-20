package ru.levelup.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class TestDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("test-user")) {
            return new User(username, encoder.encode("123"),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }

        throw new UsernameNotFoundException("User " + username + " not found.");
    }
}
