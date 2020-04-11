package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.levelup.db.UsersRepository;
import ru.levelup.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersRestController {
    @Autowired
    private UsersRepository repository;

    @GetMapping("/api/users")
    public List<User> findUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        for (User user : repository.findAll()) {
            allUsers.add(user);
        }
        return allUsers;
    }

    @GetMapping("/api/logins")
    public List<String> findUserLogins() {
        ArrayList<String> allUsers = new ArrayList<>();
        for (User user : repository.findAll()) {
            allUsers.add(user.getLogin());
        }
        return allUsers;
    }

    @GetMapping("/api/user-stat")
    public MyRestResponse getUserStat() {
        MyRestResponse response = new MyRestResponse();
        response.setSomeone(repository.findAll().iterator().next());
        response.setTitle("test");
        response.setUsersCount(repository.count());

        return response;
    }
}
