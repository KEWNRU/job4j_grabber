package ru.job4j.parking.park;

import org.junit.jupiter.api.Test;
import ru.job4j.parking.car.Car;
import ru.job4j.parking.car.Truck;

import static org.junit.Assert.assertEquals;

class ParkTest {

    @Test
    void addParkCar() {
      Park park = new Park();
      Car car = new Car(1);
      park.addCar(car);
      assertEquals(1, park.getParking().size());
    }

    @Test
    void addParkTruck() {
        Park park = new Park();
        Truck truck = new Truck(2);
        park.addTruck(truck);
        assertEquals(1, park.getParking().size());
    }

    @Test
    void addParkTruckInCar() {
        Park park = new Park();
        park.setParkingTruck(0);
        Truck truck = new Truck(2);
        park.addTruck(truck);
        assertEquals(1, park.getParking().size());
    }
}