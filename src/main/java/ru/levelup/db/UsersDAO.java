package ru.levelup.db;

import com.sun.istack.Nullable;
import ru.levelup.model.Color;
import ru.levelup.model.Group;
import ru.levelup.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

public class UsersDAO {
    private EntityManager manager;

    public UsersDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
        this.manager = manager;
    }

    public Group createGroup(String name) {
        Group group = new Group(name);
        manager.getTransaction().begin();
        try {
            manager.persist(group);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

        return group;
    }

    @Nullable
    public Group findGroupByName(String name) {
        try {
            return manager.createQuery("SELECT g from Group g WHERE g.name = :nameToSearch", Group.class)
                    .setParameter("nameToSearch", name)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    public User createUser(String login, Color color, Group group) {
        User user = new User();
        user.setLogin(login);
        user.setColor(color);
        user.setGroup(group);

        manager.getTransaction().begin();
        try {
            manager.persist(user);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

        return user;
    }

    @Nullable
    public User findUserByLogin(String login) {
        try {
            return manager.createQuery("from User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }
}
