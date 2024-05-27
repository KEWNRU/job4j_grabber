package ru.job4j.parking.park;

import org.junit.jupiter.api.Test;
import ru.job4j.parking.car.Car;
import ru.job4j.parking.model.Transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class ParkTest {

    @Test
    void addParkCarNull() {
        Parking parking = new Park(0, 0);
        Transport car = new Car(2);
        assertThrows(RuntimeException.class, () -> parking.add(car));
    }
    @Test
    void addParkCar() {
        Parking parking = new Park(2, 2);
        Transport car = new Car(1);
        parking.add(car);
        assertEquals(1, parking.getCountParkingBusy());
    }

    @Test
    void addParkTruck() {
        Parking parking = new Park(1, 2);
        Transport truck = new Car(2);
        parking.add(truck);
        assertEquals(1, parking.getCountParkingBusy());
    }

    @Test
    void addParkTruckNull() {
        Parking parking = new Park(0, 2);
        Transport truck = new Car(2);
        parking.add(truck);
        assertEquals(2, parking.getCountParkingBusy());
    }

    @Test
    void addParkTruckAndAddCar() {
        Parking parking = new Park(1, 1);
        Transport car = new Car(1);
        Transport truck = new Car(2);
        parking.add(truck);
        parking.add(car);
        assertEquals(2, parking.getCountParkingBusy());
    }

    @Test
    void addParkCarNotSize() {
        Parking parking = new Park(1, 0);
        Transport car = new Car(1);
        Transport truck = new Car(2);
        assertThrows(RuntimeException.class, () -> parking.add(car));
    }

    @Test
    void addParkCarTwoOne() {
        Parking parking = new Park(1, 1);
        Transport car = new Car(1);
        Transport car1 = new Car(1);
        parking.add(car1);
        assertThrows(RuntimeException.class, () -> parking.add(car));
    }

}