package ru.job4j_spring.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.job4j_spring.models.AdUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Repository
public interface DaoAdUserJPA extends CrudRepository<AdUser, Integer>  {

   @Query("FROM AdUser as aduser where aduser.name = ?1 and aduser.password = ?2")
    AdUser findAdUserByNamePass(String name, String password);

    @Query("FROM AdUser as aduser where aduser.name = ?1")
    AdUser findAdUserByName(String name);
}
