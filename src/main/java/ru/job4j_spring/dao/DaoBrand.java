package ru.job4j_spring.dao;


import ru.job4j_spring.models.Brand;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoBrand {

    boolean addBrand(Brand brand);

    Brand findBrandById(String id);

    List<Brand> findAllBrand();
}
