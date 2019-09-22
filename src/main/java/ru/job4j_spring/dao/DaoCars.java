package ru.job4j_spring.dao;


import ru.job4j_spring.filters.FilterList;
import ru.job4j_spring.models.Cars;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoCars {

    boolean addCar(Cars cars);

    boolean updateCar(Cars cars);

    List<Cars> findAllCars();

    Cars findCarById(String id);

    List<Cars> filtersCars(FilterList filterList);
}
