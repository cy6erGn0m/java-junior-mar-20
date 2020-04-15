package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.User;

import java.util.ArrayList;

@Component
public class UserRolesService implements UserDetailsService {
    @Autowired
    private UsersDAO users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findUserByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user " + username + " found.");
        }

        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (username.equals("admin")) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(
                username, user.getEncodedPassword(), roles
        );
    }
}
