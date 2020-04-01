package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UsersDAO users;

    @GetMapping(path = "/login")
    public String loginPage(@RequestParam(required = false) String login) {
        return "login";
    }

    @PostMapping(path = "/login")
    public String doPost(HttpSession session,
                         @RequestParam("usernameField") String username,
                         @RequestParam("passwordField") String password) {
        if (session.getAttribute("verifiedUserName") != null) {
            return "redirect:/";
        }

        User user = users.findUserByLogin(username);

        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("verifiedUserName", username);
            return "redirect:/";
        } else {
            return "redirect:/login?login=" + username;
        }
    }
}
