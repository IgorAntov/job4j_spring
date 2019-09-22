package ru.job4j_spring.dao;


import ru.job4j_spring.models.AdUser;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoAdUser {

    boolean addAdUser(AdUser adUser);

    AdUser findAdUserByNamePass(String name, String password);

    AdUser findAdUserByName(String name);
}
