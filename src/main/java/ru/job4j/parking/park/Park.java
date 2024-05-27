package ru.job4j.parking.park;

import ru.job4j.parking.model.Transport;

import java.util.ArrayList;
import java.util.List;

public class Park implements Parking {

    private final List<Transport> carSpace = new ArrayList<>();
    private final List<Transport> truckSpace = new ArrayList<>();
    private final int parkingTruck;
    private final int parkingCar;

    public Park(int parkingTruck, int parkingCar) {
        this.parkingTruck = parkingTruck;
        this.parkingCar = parkingCar;
    }

    @Override
    public int getCountParkingBusy() {
        return carSpace.size() + truckSpace.size();
    }

    @Override
    public void add(Transport car) {
        if (parkingCar - carSpace.size() == 0 || getCountParkingBusy() == parkingCar + parkingTruck) {
            throw new RuntimeException("Нет мест");
        }
        if (car.getSize() == 1 && parkingCar >= carSpace.size()) {
            carSpace.add(car);
        }
        if (car.getSize() > 1 && parkingTruck >= truckSpace.size()
                && parkingTruck != 0) {
            truckSpace.add(car);
        } else if (car.getSize() > 1 && parkingTruck == truckSpace.size()
                && car.getSize() <= parkingCar - carSpace.size()) {
            for (int i = 0; i < car.getSize(); i++) {
                carSpace.add(car);
            }
        }
    }
}
