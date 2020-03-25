package ru.levelup.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class SmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
    }

    @After
    public void close() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void createUserTest() {
        User user = new User();
        user.setLogin("test-user");
        user.setColor(new Color(1, 2, 3));
        user.setPassword("111");

        Group group = new Group();
        group.setName("test-group");
        user.setGroup(group);
        user.setStatus(UserStatus.ACTIVE);

        manager.getTransaction().begin();
        manager.persist(group);
        manager.persist(user);

        User foundUser = manager.find(User.class, user.getId());
        assertNotNull(foundUser);
        assertSame(foundUser, user);

        manager.getTransaction().commit();

        manager.refresh(foundUser);
    }

    @Test
    public void testSearchNative() {
        createUserTest();

        manager.createNativeQuery("SELECT u.id from User as u")
                .getResultList();
    }


    @Test
    public void testSearch() {
        createUserTest();

        List<User> foundUsers =
                manager.createQuery("SELECT u from User u WHERE u.group.name = :group", User.class)
                        .setParameter("group", "test-group")
                        .getResultList();

        assertEquals(1, foundUsers.size());
        assertEquals("test-user", foundUsers.get(0).getLogin());
    }

    @Test
    public void testSearchGroups() {
        createUserTest();

        List<Group> foundGroups =
                manager.createQuery("SELECT u.group from User u WHERE u.status = :status", Group.class)
                        .setParameter("status", UserStatus.ACTIVE)
                        .getResultList();

        assertEquals(1, foundGroups.size());
        assertEquals("test-group", foundGroups.get(0).getName());
    }
}
