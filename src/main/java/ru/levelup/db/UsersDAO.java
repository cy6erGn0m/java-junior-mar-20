package ru.levelup.db;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.model.Color;
import ru.levelup.model.Group;
import ru.levelup.model.User;
import ru.levelup.model.UserStatus;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class UsersDAO {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Group createGroup(String name) {
        Group group = new Group(name);
        manager.persist(group);
        return group;
    }

    public List<Group> findAllGroups() {
        return manager.createQuery("SELECT g from Group g", Group.class).getResultList();
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

    @Transactional
    public User createUser(String login, String encodedPassword, Color color, Group group) {
        User user = new User();
        user.setLogin(login);
        user.setColor(color);
        user.setGroup(group);
        user.setEncodedPassword(encodedPassword);

        manager.persist(user);

        return user;
    }

    @Nullable
    public User findUserByLogin(String login) {
        try {
            return manager.createQuery("SELECT u from User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    @Transactional
    public void banUser(User user) {
        user.setStatus(UserStatus.BANNED);
    }

    @Transactional
    public void banUserBefore(Date registeredBefore) {
            manager.createQuery("UPDATE User set status = :status " +
                    "where registrationDate < :before")
                    .setParameter("status", UserStatus.BANNED)
                    .setParameter("before", registeredBefore)
                    .executeUpdate();
    }
}
