package ru.job4j_spring.dao;


import ru.job4j_spring.models.Engine;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoEngine {

    boolean addEngine(Engine engine);

    Engine findEngineById(String id);

    List<Engine> findAllEngine();
}
