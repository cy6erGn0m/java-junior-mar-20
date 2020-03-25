package ru.levelup.web;

import ru.levelup.db.UsersDAO;
import ru.levelup.model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/user/profile/*", "/login" })
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("verifiedUserName") != null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        String username = req.getParameter("usernameField");
        String password = req.getParameter("passwordField");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UsersDAO users = new UsersDAO(manager);

        User user = null;
        try {
            if (username != null) {
                user = users.findUserByLogin(username);
            }
        } finally {
            manager.close();
        }

        if (user != null && password != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("verifiedUserName", username);
            resp.sendRedirect(req.getContextPath());
        } else {
            resp.sendRedirect("login?login=" + username);
        }
    }
}
