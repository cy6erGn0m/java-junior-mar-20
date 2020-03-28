package ru.levelup.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class AppConfiguration {
    @Bean
    @Qualifier("defaultManager")
    public EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Bean
    @Qualifier("zzzzManager")
    public EntityManager getEntityManager1(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
}
