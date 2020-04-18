package ru.levelup.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.Color;
import ru.levelup.model.Group;
import ru.levelup.model.User;
import ru.levelup.web.LoginController;
import ru.levelup.web.WebConfiguration;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, WebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UsersDAO users;

    @Autowired
    @Qualifier("springSecurityFilterChain")
    private Filter securityFilter;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(securityFilter)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void loginFormViewTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/login-page")
        ).andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "test-user", roles = "USER")
    public void loginFormViewWithSessionTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/login-page")
        ).andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    @Ignore
    public void loginFormValidTest() throws Exception {
        Group group = users.createGroup("test-group");
        User user = users.createUser("test-login", "123",
                new Color(1, 2, 3), group);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("usernameField", "test-login")
                        .param("passwordField", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(request().sessionAttribute(LoginController.VERIFIED_USER_NAME_ATTRIBUTE,
                        "test-login"))
                .andReturn();
    }

    @Test
    @Ignore
    public void loginFormInvalidTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("usernameField", "invalid-login")
                        .param("passwordField", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?login=invalid-login"))
                .andReturn();
    }

    @Test
    @Ignore
    public void loginFormAlreadyLoggedInTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("usernameField", "test-login")
                        .param("passwordField", "123")
                        .sessionAttr(LoginController.VERIFIED_USER_NAME_ATTRIBUTE, "another-login")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andReturn();
    }
}
