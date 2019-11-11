package ru.job4j_spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j_spring.models.Drive;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Repository
public interface DaoDriveJPA extends CrudRepository<Drive, Integer> {
}
