package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levelup.db.UsersRepository;
import ru.levelup.model.User;
import ru.levelup.model.UserStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/api/ban-user")
    public String badUser(@RequestParam String login) {
        User user = repository.findByLogin(login);
        user.setStatus(UserStatus.BANNED);
        repository.save(user);
        return "OK";
    }

    @GetMapping("/api/banned-users")
    public List<User> findBannedUsers(@RequestParam int pageNumber) {
        Page<User> page = repository.findByStatusOrderByLogin(UserStatus.BANNED,
                PageRequest.of(pageNumber - 1, 10));

        return page.get().collect(Collectors.toList());
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
