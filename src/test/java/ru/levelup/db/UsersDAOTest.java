package ru.levelup.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.model.Color;
import ru.levelup.model.Group;
import ru.levelup.model.User;
import ru.levelup.tests.TestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UsersDAOTest {
    @Autowired
    private UsersDAO users;

    @Autowired
    private EntityManager manager;

    @Test
    public void createGroup() {
        Group created = users.createGroup("test-group");
        assertNotNull(created);
        assertEquals("test-group", created.getName());
        assertNotEquals(0, created.getId());

        findGroup(created.getId());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Group findGroup(int id) {
        Group foundById = manager.find(Group.class, id);
        manager.refresh(foundById);
        return foundById;
    }

    @Test
    public void createGroupDuplicate() {
        Group created = users.createGroup("test-group-3");
        assertNotNull(created);

        try {
            users.createGroup("test-group-3");
            fail("createGroup should fail for the same group name");
        } catch (PersistenceException expected) {
        }
    }

    @Test
    public void findGroupByName() {
        Group g = new Group("test-group-2");
        manager.getTransaction().begin();
        manager.persist(g);
        manager.getTransaction().commit();

        Group found = users.findGroupByName("test-group-2");
        assertNotNull(found);
        assertEquals("test-group-2", found.getName());

        assertNull(users.findGroupByName("some-group"));
    }

    @Test
    public void createUser() {
        Group g = new Group("test-group-4");
        manager.getTransaction().begin();
        manager.persist(g);
        manager.getTransaction().commit();

        User user = users.createUser("login1", "pass", new Color(1, 2, 3), g);
        assertNotNull(user);
        assertEquals("login1", user.getLogin());
        assertNotNull(user.getGroup());
        assertEquals(g.getId(), user.getGroup().getId());
        assertNotEquals(0, user.getId());

        User found = manager.find(User.class, user.getId());
        assertNotNull(found);
        manager.refresh(found);
    }

    @Test
    public void findUserByLogin() {
        Group g = new Group("test-group-999");
        User user = new User();
        user.setLogin("login2");
        user.setGroup(g);
        user.setColor(new Color(2, 3, 4));
        user.setEncodedPassword("111");

        manager.getTransaction().begin();
        manager.persist(g);
        manager.persist(user);
        manager.getTransaction().commit();

        User found = users.findUserByLogin("login2");
        assertNotNull(found);
        assertEquals("login2", found.getLogin());
        assertEquals("test-group-999", found.getGroup().getName());
    }
}