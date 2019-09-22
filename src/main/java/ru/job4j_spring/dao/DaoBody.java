package ru.job4j_spring.dao;


import ru.job4j_spring.models.Body;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoBody {

    boolean addBody(Body body);

    Body findBodyById(String id);

    List<Body> findAllBody();
}
