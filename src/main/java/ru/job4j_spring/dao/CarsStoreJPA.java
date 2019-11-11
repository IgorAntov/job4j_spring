package ru.job4j_spring.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j_spring.filters.DoFilter;
import ru.job4j_spring.filters.FilterList;
import ru.job4j_spring.models.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Repository(value = "StoreJPA")
public class CarsStoreJPA implements DAO {

    @Autowired
    private DaoAdUserJPA daoAdUserJPA;
    @Autowired
    private DaoBodyJPA daoBodyJPA;
    @Autowired
    private DaoBrandJPA daoBrandJPA;
    @Autowired
    private DaoCarsJPA daoCarsJPA;
    @Autowired
    private DaoDriveJPA daoDriveJPA;
    @Autowired
    private DaoEngineJPA daoEngineJPA;
    @Autowired
    private DaoTransmissionJPA daoTransmissionJPA;
    @Autowired
    private DaoWheelJPA daoWheelJPA;

    @Autowired
    DoFilter doFilter;

    @Autowired
    private EntityManager entityManager;

    public boolean addAdUser(AdUser adUser) {
        daoAdUserJPA.save(adUser);
        return true;
    }

    @Override
    public AdUser findAdUserByNamePass(String name, String password) {
        return daoAdUserJPA.findAdUserByNamePass(name, password);
    }

    @Override
    public AdUser findAdUserByName(String name) {
        return daoAdUserJPA.findAdUserByName(name);
    }

    @Override
    public boolean addBody(Body body) {
        daoBodyJPA.save(body);
        return true;
    }

    @Override
    public Body findBodyById(String id) {
        return daoBodyJPA.findById(Integer.parseInt(id)).get();
    }

    @Override
    public List<Body> findAllBody() {
        var itemList = new ArrayList<Body>();
        daoBodyJPA.findAll().forEach(e -> itemList.add(e));
        return itemList;
    }


    @Override
    public boolean addBrand(Brand brand) {
        daoBrandJPA.save(brand);
        return true;
    }

    @Override
    public Brand findBrandById(String id) {
        return daoBrandJPA.findById(Integer.parseInt(id)).get();
    }

    @Override
    public List<Brand> findAllBrand() {
        var itemList = new ArrayList<Brand>();
        daoBrandJPA.findAll().forEach(e -> itemList.add(e));
        return itemList;
    }

    @Override
    public boolean addCar(Cars cars) {
        daoCarsJPA.save(cars);
        return true;
    }

    @Override
    public boolean updateCar(Cars cars) {
        return false;
    }

    @Override
    public List<Cars> findAllCars() {
        var itemList = new ArrayList<Cars>();
        daoCarsJPA.findAll().forEach(e -> itemList.add(e));
        return itemList;
    }

    @Override
    public Cars findCarById(String id) {
        return daoCarsJPA.findById(Integer.parseInt(id)).get();
    }

    @Override
    @Transactional
    public List<Cars> filtersCars(FilterList filterList) {
        Session session = entityManager.unwrap(Session.class);
        return  doFilter.filtersCars(filterList, session);
    }

    @Override
    public boolean addDrive(Drive drive) {
        daoDriveJPA.save(drive);
        return true;
    }

    @Override
    public Drive findDriveById(String id) {
        return daoDriveJPA.findById(Integer.parseInt(id)).get();
    }

    @Override
    public List<Drive> findAllDrive() {
        var itemList = new ArrayList<Drive>();
        daoDriveJPA.findAll().forEach(e -> itemList.add(e));
        return itemList;
    }

    @Override
    public boolean addEngine(Engine engine) {
        daoEngineJPA.save(engine);
        return true;
    }

    @Override
    public Engine findEngineById(String id) {
        return daoEngineJPA.findById(Integer.parseInt(id)).get();
    }

    @Override
    public List<Engine> findAllEngine() {
        var itemList = new ArrayList<Engine>();
        daoEngineJPA.findAll().forEach(e -> itemList.add(e));
        return itemList;
    }

    @Override
    public boolean addTransmission(Transmission transmission) {
        daoTransmissionJPA.save(transmission);
        return true;
    }

    @Override
    public Transmission findTransmissionById(String id) {
        return daoTransmissionJPA.findById(Integer.parseInt(id)).get();
    }

    @Override
    public List<Transmission> findAllTransmission() {
        var itemList = new ArrayList<Transmission>();
        daoTransmissionJPA.findAll().forEach(e -> itemList.add(e));
        return itemList;
    }

    @Override
    public boolean addWheel(Wheel wheel) {
        daoWheelJPA.save(wheel);
        return true;
    }

    @Override
    public Wheel findWheelById(String id) {
        return daoWheelJPA.findById(Integer.parseInt(id)).get();
    }

    @Override
    public List<Wheel> findAllWheel() {
        var itemList = new ArrayList<Wheel>();
        daoWheelJPA.findAll().forEach(e -> itemList.add(e));
        return itemList;
    }
}
