package ru.job4j_spring.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j_spring.config.AppConfig;
import ru.job4j_spring.filters.FilterList;
import ru.job4j_spring.models.*;

import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CarsStoreTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    @Qualifier("Store")
    private DAO carsStore;

    @Autowired
    @Qualifier("StoreJPA")
    private DAO carsStoreJPA;

    @Test
    public void whenAddUserToDbThenGetNewUserInDb() {
        AdUser adUser = new AdUser();
        adUser.setName("name");
        carsStore.addAdUser(adUser);
        Assert.assertTrue(carsStore.findAdUserByName("name").equals(adUser));
    }

    @Test
    public void whenAddUserToDbBySpringJPAThenGetNewUserInDb() {
        AdUser adUser = new AdUser();
        adUser.setName("name2");
        carsStoreJPA.addAdUser(adUser);
        Assert.assertTrue(carsStoreJPA.findAdUserByName("name2").equals(adUser));
    }

    @Test
    @Transactional
    public void whenAddUserToDbBySpringDJPAThenCanFinedUserByNameAndPass() {
        AdUser adUser = new AdUser();
        adUser.setName("username");
        adUser.setPassword("userpass");
        carsStoreJPA.addAdUser(adUser);
        AdUser userFromDB = carsStoreJPA.findAdUserByNamePass("username", "userpass");
        Assert.assertTrue(userFromDB.equals(adUser));
    }

    @Test
    public void whenAddUserToDbBySpringDJpaThenGetNewUserInDb() {
        AdUser adUser = new AdUser();
        adUser.setName("name3");
        carsStoreJPA.addAdUser(adUser);
        Assert.assertTrue(carsStoreJPA.findAdUserByName("name3").equals(adUser));
    }

    @Test
    public void whenAddBodyToDbBySpringDJpaThenGetThisBodyByIdFromDB() {
        Body body = new Body();
        body.setName("body");
        carsStoreJPA.addBody(body);
        int id = 1;
        Assert.assertThat(id , is(carsStoreJPA.findBodyById(String.valueOf(id)).getId()));
    }

    @Test
    public void whenAddBodyToDbBySpringDJpaThenGetAtLeastOneBody() {
        Body body = new Body();
        body.setName("body2");
        carsStoreJPA.addBody(body);
        Assert.assertTrue(!carsStoreJPA.findAllBody().isEmpty());
    }

    @Test
    public void whenAddBrandToDbBySpringDJpaThenGetThisBrandByIdFromDB() {
        Brand brand = new Brand();
        brand.setName("brand");
        carsStoreJPA.addBrand(brand);
        int id = 1;
        Assert.assertThat(id , is(carsStoreJPA.findBrandById(String.valueOf(id)).getId()));
    }

    @Test
    public void whenAddBrandToDbBySpringDJpaThenGetAtLeastOneBrand() {
        Brand brand = new Brand();
        brand.setName("brand2");
        carsStoreJPA.addBrand(brand);
        Assert.assertTrue(!carsStoreJPA.findAllBrand().isEmpty());
    }

    @Test
    public void whenAddDriveToDbBySpringDJpaThenGetThisDriveByIdFromDB() {
        Drive drive = new Drive();
        drive.setName("drive");
        carsStoreJPA.addDrive(drive);
        int id = 1;
        Assert.assertThat(id , is(carsStoreJPA.findDriveById(String.valueOf(id)).getId()));
    }

    @Test
    public void whenAddDriveToDbBySpringDJpaThenGetAtLeastOneDrive() {
        Drive drive = new Drive();
        drive.setName("drive2");
        carsStoreJPA.addDrive(drive);
        Assert.assertTrue(!carsStoreJPA.findAllDrive().isEmpty());
    }

    @Test
    public void whenAddEngineToDbBySpringDJpaThenGetThisEngineByIdFromDB() {
        Engine engine = new Engine();
        engine.setName("engine");
        carsStoreJPA.addEngine(engine);
        int id = 1;
        Assert.assertThat(id , is(carsStoreJPA.findEngineById(String.valueOf(id)).getId()));
    }

    @Test
    public void whenAddEngineToDbBySpringDJpaThenGetAtLeastOneEngine() {
        Engine engine = new Engine();
        engine.setName("engine2");
        carsStoreJPA.addEngine(engine);
        Assert.assertTrue(!carsStoreJPA.findAllEngine().isEmpty());
    }

    @Test
    public void whenAddTransmissionToDbBySpringDJpaThenGetThisTransmissionByIdFromDB() {
        Transmission transmission = new Transmission();
        transmission.setName("transmission");
        carsStoreJPA.addTransmission(transmission);
        int id = 1;
        Assert.assertThat(id , is(carsStoreJPA.findTransmissionById(String.valueOf(id)).getId()));
    }

    @Test
    public void whenAddTransmissionToDbBySpringDJpaThenGetAtLeastOneTransmission() {
        Transmission transmission = new Transmission();
        transmission.setName("transmission2");
        carsStoreJPA.addTransmission(transmission);
        Assert.assertTrue(!carsStoreJPA.findAllTransmission().isEmpty());
    }

    @Test
    public void whenAddWheelToDbBySpringDJpaThenGetThisWheelByIdFromDB() {
        Wheel wheel = new Wheel();
        wheel.setName("wheel");
        carsStoreJPA.addWheel(wheel);
        int id = 1;
        Assert.assertThat(id , is(carsStoreJPA.findWheelById(String.valueOf(id)).getId()));
    }

    @Test
    public void whenAddWheelToDbBySpringDJpaThenGetAtLeastOneWheel() {
        Wheel wheel = new Wheel();
        wheel.setName("wheel2");
        carsStoreJPA.addWheel(wheel);
        Assert.assertTrue(!carsStoreJPA.findAllWheel().isEmpty());
    }
    
    @Test
    @Transactional
    public void whenAddNewCarToDbBySpringDJpaThenGetAtLeastOneCar() {
        AdUser adUser = new AdUser();
        adUser.setName("name4");
        carsStoreJPA.addAdUser(adUser);
        Body body = new Body();
        body.setName("body3");
        carsStoreJPA.addBody(body);
        carsStoreJPA.addBody(body);
        Cars car = new Cars();
        car.setAduser(adUser);
        car.setBody(body);
        car.setAddesc("desc");
        carsStoreJPA.addCar(car);
        FilterList filterList = new FilterList();
        filterList.setPeriod("");
        filterList.setBrand("");
        filterList.setFoto("");
        carsStoreJPA.filtersCars(filterList);
    }
}