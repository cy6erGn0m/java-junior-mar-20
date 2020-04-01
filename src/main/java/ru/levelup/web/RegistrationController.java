package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.Color;
import ru.levelup.model.Group;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    private UsersDAO users;

    @GetMapping(path = "/user/register")
    public String getRegistrationForm(ModelMap model) {
        List<Group> groups = users.findAllGroups();

        RegistrationForm form = new RegistrationForm();
        form.setLogin("");
        form.setPassword("");
        form.setGroups(groups);
        form.setSelectedGroup(groups.get(0));

        model.addAttribute("form", form);

        return "register";
    }

    @PostMapping(path = "/user/register")
    public String processRegistrationForm(@RequestParam String login,
                                          @RequestParam String password,
                                          @RequestParam("group") String groupName) {
        Group group = users.findGroupByName(groupName);
        if (group == null) {
            throw new IllegalStateException("No group " + groupName + " found");
        }

        users.createUser(login, password, new Color(1, 2, 3), group);

        return "redirect:/login?login=" + login;
    }
}
