package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.db.UsersRepository;
import ru.levelup.model.User;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {
    public static final String
            VERIFIED_USER_NAME_ATTRIBUTE = "verifiedUserName";

    @Autowired
    private UsersRepository users;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(path = "/login-page")
    public String loginPage(@RequestParam(required = false) String login,
                            Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        return "login";
    }

//    @PostMapping(path = "/submit-login-form")
    public String processLoginForm(HttpSession session,
                                   @RequestParam("usernameField") String username,
                                   @RequestParam("passwordField") String password) {
        if (session.getAttribute(VERIFIED_USER_NAME_ATTRIBUTE) != null) {
            return "redirect:/";
        }

        User user = users.findByLogin(username);

        if (user != null && encoder.matches(password, user.getEncodedPassword())) {
            session.setAttribute(VERIFIED_USER_NAME_ATTRIBUTE, username);
            return "redirect:/";
        } else {
            return "redirect:/login?login=" + username;
        }
    }
}
