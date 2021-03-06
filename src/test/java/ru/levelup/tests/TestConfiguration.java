package ru.levelup.tests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.levelup.web.ProdConfiguration;
import ru.levelup.web.WebConfiguration;

@Configuration
@ComponentScan(basePackages = {"ru.levelup.web", "ru.levelup.db"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = { ProdConfiguration.class, WebConfiguration.class }
        ))
public class TestConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("TestPersistenceUnit");
        return bean;
    }

//    @Bean
//    public UserDetailsService testService() {
//        return new TestDetailsService();
//    }
}
