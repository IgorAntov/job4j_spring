package ru.job4j_spring.models;


import javax.persistence.*;
import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table(name = "drive",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_drive_name", columnNames = { "name" }) })
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String name;

    public Drive() {
    }

    public Drive(String name) {
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
        Drive drive = (Drive) o;
        return id == drive.id &&
                Objects.equals(name, drive.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
