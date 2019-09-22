package ru.job4j_spring.models;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table(name = "cars")
@FilterDef(name="brandFilter"
        , parameters=
@ParamDef( name="brand", type = "integer")


)
@FilterDef(name="dateFilter",
        parameters=@ParamDef( name="dateParam", type="date" ) )
@Filter(name = "brandFilter", condition = "brand_id = :brand")
@Filter(
        name = "dateFilter",
        condition="created >= :dateParam"
)
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String addesc;
    private String cost;
    private String mileage;
    private String power;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created = new Date();
    @ManyToOne
    @JoinColumn(name = "brand_id", foreignKey=@ForeignKey(name = "fk_brand_id"))
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "body_id", foreignKey=@ForeignKey(name = "fk_body_id"))
    private Body body;
    @ManyToOne
    @JoinColumn(name = "drive_id", foreignKey=@ForeignKey(name = "fk_drive_id"))
    private Drive drive;
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey=@ForeignKey(name = "fk_engine_id"))
    private Engine engine;
    @ManyToOne
    @JoinColumn(name = "transmission_id", foreignKey=@ForeignKey(name = "fk_transmission_id"))
    private Transmission transmission;
    @ManyToOne
    @JoinColumn(name = "wheel_id", foreignKey=@ForeignKey(name = "fk_wheel_id"))
    private Wheel wheel;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey=@ForeignKey(name = "fk_user_id"))
    private AdUser aduser;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "car_image",
            joinColumns =  @JoinColumn(name = "cars_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name = "fk_car_id")) ,
            foreignKey= @ForeignKey(name = "fk_cars_id") ,
            inverseJoinColumns =  @JoinColumn(name = "image_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name = "fk_image_id"))
    )


    Set<Image> imageSet = new HashSet<>();

    public Cars() {
        this.status = "not sold";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdUser getAduser() {
        return aduser;
    }

    public void setAduser(AdUser aduser) {
        this.aduser = aduser;
    }

    public String getAddesc() {
        return addesc;
    }

    public void setAddesc(String addesc) {
        this.addesc = addesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return id == cars.id &&
                cost == cars.cost &&
                mileage == cars.mileage &&
                power == cars.power &&
                Objects.equals(title, cars.title) &&
                Objects.equals(addesc, cars.addesc) &&
                Objects.equals(brand, cars.brand) &&
                Objects.equals(body, cars.body) &&
                Objects.equals(drive, cars.drive) &&
                Objects.equals(engine, cars.engine) &&
                Objects.equals(transmission, cars.transmission) &&
                Objects.equals(wheel, cars.wheel) &&
                Objects.equals(aduser, cars.aduser) &&
                Objects.equals(imageSet, cars.imageSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, mileage, power, brand, body, drive, engine, transmission, wheel, addesc);
    }
}