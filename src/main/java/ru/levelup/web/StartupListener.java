package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.levelup.db.UsersDAO;

@Component
public class StartupListener {
    @Autowired
    private UsersDAO users;

    @EventListener
    public void applicationStarted(ContextRefreshedEvent event) {
        if (users.findGroupByName("test") == null) {
            users.createGroup("test");
        }
    }
}
