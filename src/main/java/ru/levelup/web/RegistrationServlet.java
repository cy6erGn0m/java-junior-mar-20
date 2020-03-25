package ru.levelup.web;

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

@WebServlet(urlPatterns = "/user/register")
public class RegistrationServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        List<Group> groups;
        try {
            groups = new UsersDAO(manager).findAllGroups();
        } finally {
            manager.close();
        }

        RegistrationForm form = new RegistrationForm();
        form.setLogin("");
        form.setPassword("");
        form.setGroups(groups);
        form.setSelectedGroup(groups.get(0));

        req.setAttribute("form", form);

        req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String groupName = req.getParameter("group");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UsersDAO users = new UsersDAO(manager);
        try {
            Group group = users.findGroupByName(groupName);
            if (group == null) {
                throw new IllegalStateException("No group " + groupName + " found");
            }

            users.createUser(login, password, new Color(1, 2, 3), group);
        } finally {
            manager.close();
        }

        resp.sendRedirect(req.getContextPath() + "/login?login=" + login);
    }
}
