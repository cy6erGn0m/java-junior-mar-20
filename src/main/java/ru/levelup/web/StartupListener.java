package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.Color;
import ru.levelup.model.Group;

@Component
public class StartupListener {
    @Autowired
    private UsersDAO users;

    @EventListener
    @Transactional
    public void applicationStarted(ContextRefreshedEvent event) {
        if (users.findGroupByName("test") == null) {
            Group group = users.createGroup("test");
            users.createUser("root", "111", new Color(1, 2, 3), group);
        }
    }
}
