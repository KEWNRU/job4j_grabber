package ru.job4j.parking.park;

import ru.job4j.parking.model.Transport;

import java.util.ArrayList;
import java.util.List;

public class Park implements Parking {

    private final List<Transport> parking = new ArrayList<>();
    private int parkingTruck = 10;
    private int parkingCar = 20;

    public List<Transport> getParking() {
        return parking;
    }

    public void setParkingTruck(int parkingTruck) {
        this.parkingTruck = parkingTruck;
    }

    @Override
    public void addCar(Transport car) {
        if (car.getSize() == 1 && parkingCar != 0) {
            parking.add(car);
            parkingCar--;
        }
    }

    @Override
    public void addTruck(Transport truck) {
        if (truck.getSize() > 1 && parkingTruck != 0) {
            parking.add(truck);
            parkingTruck--;
        }
        if (parkingTruck == 0 && truck.getSize() > 1) {
            parking.add(truck);
            parkingCar -= truck.getSize();
        }
    }
}
