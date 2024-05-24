package ru.job4j.parking.car;

import ru.job4j.parking.model.Transport;

public class Truck implements Transport {
    int truckSize;

    public Truck(int truckSize) {
        this.truckSize = truckSize;
    }

    @Override
    public int getSize() {
        return truckSize;
    }
}
