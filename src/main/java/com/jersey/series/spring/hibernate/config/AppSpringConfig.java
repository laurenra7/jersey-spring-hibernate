package com.jersey.series.spring.hibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Annotation-based Spring configuration instead of using XML files.
 */

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.jersey.series.spring.hibernate.dao"),
        @ComponentScan("com.jersey.series.spring.hibernate.db.resources"),
        @ComponentScan("com.jersey.series.spring.hibernate.model"),
        @ComponentScan("com.jersey.series.spring.hibernate.service")})
public class AppSpringConfig {

    @Bean
    public LocalEntityManagerFactoryBean appEntityManagerFactoryBean() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager appJpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(appEntityManagerFactoryBean().getObject());
        return transactionManager;
    }

}
