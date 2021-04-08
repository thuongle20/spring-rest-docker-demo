package com.spys.ms.sample.config;

import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@ComponentScan("com.spys.ms")
@Configuration
public class UnitTestApplicationContext
{

    @Bean
    public SessionFactory sessionFactory()
    {
        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        return sessionFactory;
    }
}
