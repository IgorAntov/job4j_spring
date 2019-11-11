package ru.job4j_spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Configuration
public class SessionFactoryConfig {

    @Autowired
    DataSource dataSource;
    @Autowired
    Properties hibernateProperties;

    /**
     * Hibernate SessionFectory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{"ru.job4j_spring"});
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.afterPropertiesSet();
        return sessionFactory;
    }
}
