package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.db.UsersRepository;
import ru.levelup.model.UserStatus;

@Component
public class AdminService {
    @Autowired
    private UsersRepository users;

    @Transactional
    @Secured("ROLE_ADMIN")
    public void banBadUsers() {
        users.findUsersScoreTooBig(10000).forEach(user -> {
            user.setStatus(UserStatus.BANNED);
        });
    }
}
