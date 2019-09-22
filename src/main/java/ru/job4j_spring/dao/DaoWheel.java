package ru.job4j_spring.dao;


import ru.job4j_spring.models.Wheel;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoWheel {

    boolean addWheel(Wheel wheel);

    Wheel findWheelById(String id);

    List<Wheel> findAllWheel();
}
