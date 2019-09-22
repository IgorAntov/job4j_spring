package ru.job4j_spring.filters;

import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class FilterList {

    private String brand;
    private String foto;
    private String period;

    public FilterList() {

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilterList that = (FilterList) o;
        return Objects.equals(brand, that.brand) &&
                Objects.equals(foto, that.foto) &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {

        return Objects.hash(brand, foto, period);
    }
}

