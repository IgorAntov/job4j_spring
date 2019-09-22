package ru.job4j_spring.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table(name = "wheel",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_wheel_name", columnNames = { "name" }) })
public class Wheel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String name;

    public Wheel() {
    }

    public Wheel(String name) {
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
        Wheel wheel = (Wheel) o;
        return id == wheel.id &&
                Objects.equals(name, wheel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
