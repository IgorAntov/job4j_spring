package ru.job4j_spring.models;


import javax.persistence.*;
import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table(name = "brand",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_brand_name", columnNames = { "name" }) })
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String name;

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand1 = (Brand) o;
        return id == brand1.id &&
                Objects.equals(name, brand1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

