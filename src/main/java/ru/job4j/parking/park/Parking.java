package ru.job4j.parking.park;

import ru.job4j.parking.model.Transport;

public interface Parking {

    void add(Transport car);

    int getCountParkingBusy();
}
