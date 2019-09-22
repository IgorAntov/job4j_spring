package ru.job4j_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j_spring.dao.CarsStore;
import ru.job4j_spring.models.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Component
public class CarsService {
    private CarsStore carsStore;

    @Autowired
    public CarsService(CarsStore carsStore) {
        this.carsStore = carsStore;
        init();
    }

    /**
     * DataBase SubTypes Initialisation.
     */
    public CarsService init() {
        bodyInit();
        brandInit();
        driveInit();
        engineInit();
        transmissionInit();
        wheelInit();
        return null;
    }

    /**
     * Body Types Init
     */
    private void bodyInit() {
        carsStore.addBody(new Body("Седан"));
        carsStore.addBody(new Body("Хэтчбэк"));
        carsStore.addBody(new Body("Универсал"));
    }

    /**
     * Brand Types Init
     */
    private void brandInit() {
        carsStore.addBrand(new Brand("Audi"));
        carsStore.addBrand(new Brand("Honda"));
        carsStore.addBrand(new Brand("BMV"));
    }

    /**
     * Drive Types Init
     */

    private void driveInit() {
        carsStore.addDrive(new Drive("Передний"));
        carsStore.addDrive(new Drive("Полный"));
        carsStore.addDrive(new Drive("Задний"));
    }

    /**
     * Engine Types Init
     */
    private void engineInit() {
        carsStore.addEngine(new Engine("Бензин"));
        carsStore.addEngine(new Engine("Дизель"));
    }

    /**
     * Transmission Types Init
     */
    private void transmissionInit() {
        carsStore.addTransmission(new Transmission("Механика"));
        carsStore.addTransmission(new Transmission("Автомат"));
    }

    /**
     * Wheel Types Init
     */
    private void wheelInit() {
        carsStore.addWheel(new Wheel("Левый"));
        carsStore.addWheel(new Wheel("Правый"));
    }
}

