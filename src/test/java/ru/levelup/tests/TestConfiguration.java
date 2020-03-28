package ru.levelup.tests;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.levelup.db.UsersDAO;
import ru.levelup.web.ProdConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"ru.levelup.web", "ru.levelup.db"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = ProdConfiguration.class
        ))
public class TestConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

//    @Bean
//    public UsersDAO createDAO(@Qualifier("defaultManager") EntityManager manager) {
//        return new UsersDAO(manager);
//    }
}
