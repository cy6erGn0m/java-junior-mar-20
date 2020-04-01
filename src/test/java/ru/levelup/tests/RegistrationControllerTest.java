package ru.levelup.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.levelup.db.UsersDAO;
import ru.levelup.model.Group;
import ru.levelup.web.RegistrationForm;
import ru.levelup.web.WebConfiguration;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, WebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class RegistrationControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UsersDAO users;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testRegistrationForm() throws Exception {
        Group group = users.findGroupByName("test");

        RegistrationForm form = new RegistrationForm();
        form.setLogin("");
        form.setPassword("");
        form.setGroups(Collections.singletonList(group));
        form.setSelectedGroup(group);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/register")
        ).andExpect(status().isOk())
                .andExpect(model().attribute("form", form))
                .andExpect(view().name("register"))
                .andReturn();

    }
}
