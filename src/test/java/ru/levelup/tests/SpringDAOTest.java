package ru.levelup.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.Color;
import ru.levelup.model.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class SpringDAOTest {
    @Autowired
    public UsersDAO users;

    @Test
    public void smokeTest() {
        Group g = users.createGroup("test-group");
        users.createUser("login1", "aaaa", new Color(1, 2, 3),
                g);
    }
}
