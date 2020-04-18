package ru.levelup.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.levelup.web.AdminService;
import ru.levelup.web.WebConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SecuredTest {
    @Autowired
    public AdminService service;

    @Test(expected = AccessDeniedException.class)
    @WithAnonymousUser
    public void banWithoutRights() {
        service.banBadUsers();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void banWithRights() {
        service.banBadUsers();
    }
}
