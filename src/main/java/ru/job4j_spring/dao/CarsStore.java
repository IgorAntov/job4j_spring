package ru.job4j_spring.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j_spring.filters.DoFilter;
import ru.job4j_spring.filters.FilterList;
import ru.job4j_spring.models.*;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Repository(value = "Store")
public class CarsStore implements DAO {

    @Autowired
    DaoWrepper daoWrepper;

    @Autowired
    DoFilter doFilter;

    @Override
    public boolean addCar(Cars cars) {
        daoWrepper.tx(session -> session.save(cars));
        return true;
    }

    @Override
    public boolean updateCar(Cars cars) {
        daoWrepper.txUpdate(session -> session.update(cars));
        return true;
    }

    @Override
    public boolean addAdUser(AdUser adUser) {
        daoWrepper.tx(session -> session.save(adUser));
        return true;
    }

    @Override
    public boolean addBody(Body body) {
        daoWrepper.tx(session -> session.save(body));
        return true;
    }

    @Override
    public Body findBodyById(String id) {
        return daoWrepper.tx(session -> session.get(Body.class, Integer.parseInt(id)));
    }

    @Override
    public boolean addBrand(Brand brand) {
        daoWrepper.tx(session -> session.save(brand));
        return true;    }

    @Override
    public Brand findBrandById(String id) {
        return daoWrepper.tx(session -> session.get(Brand.class, Integer.parseInt(id)));
    }

    @Override
    public boolean addDrive(Drive drive) {
        daoWrepper.tx(session -> session.save(drive));
        return true;    }

    @Override
    public Drive findDriveById(String id) {
        return daoWrepper.tx(session -> session.get(Drive.class, Integer.parseInt(id)));
    }

    @Override
    public boolean addEngine(Engine engine) {
        daoWrepper.tx(session -> session.save(engine));
        return true;    }

    @Override
    public Engine findEngineById(String id) {
        return daoWrepper.tx(session -> session.get(Engine.class, Integer.parseInt(id)));
    }

    @Override
    public Cars findCarById(String id) {
        return daoWrepper.tx(session -> session.get(Cars.class, Integer.parseInt(id)));
    }

    @Override
    public boolean addTransmission(Transmission transmission) {
        daoWrepper.tx(session -> session.save(transmission));
        return true;    }

    @Override
    public Transmission findTransmissionById(String id) {
        return daoWrepper.tx(session -> session.get(Transmission.class, Integer.parseInt(id)));
    }

    @Override
    public boolean addWheel(Wheel wheel) {
        daoWrepper.tx(session -> session.save(wheel));
        return true;    }

    @Override
    public Wheel findWheelById(String id) {
        return daoWrepper.tx(session -> session.get(Wheel.class, Integer.parseInt(id)));
    }

    @Override
    public AdUser findAdUserByNamePass(String name, String password) {
        return daoWrepper.tx(session -> {
            AdUser aduser = null;
            final Query query = session.createQuery("FROM AdUser as aduser where aduser.name = :username and aduser.password = :userpassword");
            query.setParameter("username", name);
            var rsl = query.setParameter("userpassword", password).list();
            if (!rsl.isEmpty()) {
                aduser = (AdUser) rsl.get(0);
            }
            return aduser;
        });
    }

    @Override
    public AdUser findAdUserByName(String name) {
        return daoWrepper.tx(session -> {
            AdUser aduser = null;
            final Query query = session.createQuery("FROM AdUser as aduser where aduser.name = :username");
            var rsl = query.setParameter("username", name).list();
            if (!rsl.isEmpty()) {
                aduser = (AdUser) rsl.get(0);
            }
            return aduser;
        });
    }

    @Override
    public List<Cars> findAllCars() {
        return daoWrepper.tx(session -> (List<Cars>) session.createQuery("From Cars").list());
    }

    @Override
    public List<Body> findAllBody() {
        return daoWrepper.tx(session -> (List<Body>) session.createQuery("From Body").list());
    }

    @Override
    public List<Brand> findAllBrand() {
        return daoWrepper.tx(session -> (List<Brand>) session.createQuery("From Brand").list());
    }

    @Override
    public List<Drive> findAllDrive() {
        return daoWrepper.tx(session -> (List<Drive>) session.createQuery("From Drive").list());
    }

    @Override
    public List<Engine> findAllEngine() {
        return daoWrepper.tx(session -> (List<Engine>) session.createQuery("From Engine").list());
    }

    @Override
    public List<Transmission> findAllTransmission() {
        return daoWrepper.tx(session -> (List<Transmission>) session.createQuery("From Transmission").list());
    }

    @Override
    public List<Wheel> findAllWheel() {
        return daoWrepper.tx(session -> (List<Wheel>) session.createQuery("From Wheel").list());
    }

    @Override
    public List<Cars> filtersCars(FilterList filterList) {
        Session session = daoWrepper.getSessionFactory().openSession();
        return  doFilter.filtersCars(filterList, session);
    }
}
