package ru.levelup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.Color;
import ru.levelup.model.Group;

import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    private UsersDAO users;

    @ModelAttribute("form")
    public RegistrationForm createForm() {
        List<Group> groups = users.findAllGroups();

        RegistrationForm form = new RegistrationForm();
        form.setLogin("");
        form.setPassword("");
        form.setSelectedGroupName(groups.get(0).getName());

        return form;
    }

    public RegistrationFormData createData() {
        RegistrationFormData data = new RegistrationFormData();
        data.setGroups(users.findAllGroups());
        return data;
    }

    @GetMapping(path = "/user/register")
    public String getRegistrationForm(
            ModelMap model,
            @ModelAttribute("form") RegistrationForm form) {

        model.addAttribute("data", createData());

        return "register";
    }

    @PostMapping(path = "/user/register")
    public String processRegistrationForm(
            ModelMap model,
            @Validated
            @ModelAttribute("form") RegistrationForm form,
            BindingResult validationResult
    ) {
        model.addAttribute("data", createData());

        Group group = users.findGroupByName(form.getSelectedGroupName());
        if (group == null) {
            validationResult.addError(
                    new FieldError("form", "selectedGroupName",
                            "No group " + form.getSelectedGroupName() + " found"));
        }

        if (validationResult.hasErrors()) {
            return "register";
        }

        try {
            users.createUser(form.getLogin(), form.getPassword(),
                    new Color(1, 2, 3), group);
        } catch (Throwable cause) {
            validationResult.addError(
                    new FieldError("form", "login",
                            "User with login " + form.getLogin()
                                    + " is already registered."));

            return "register";
        }

        return "redirect:/login?login=" + form.getLogin();
    }
}
