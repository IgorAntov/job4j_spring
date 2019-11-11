package ru.job4j_spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j_spring.models.Engine;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Repository
public interface DaoEngineJPA extends CrudRepository<Engine, Integer> {
}
