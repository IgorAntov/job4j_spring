package ru.job4j_spring.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Configuration
@Import({MvcConfiguration.class, DataSourceConfig.class, SessionFactoryConfig.class, EntityManagerFactoryConfig.class})
public class AppConfig {

}
