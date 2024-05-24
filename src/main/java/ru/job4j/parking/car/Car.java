package ru.job4j.parking.car;

import ru.job4j.parking.model.Transport;

public class Car implements Transport {

    int carSize;

    public Car(int carSize) {
        this.carSize = carSize;
    }

    @Override
    public int getSize() {
        return carSize;
    }
}
