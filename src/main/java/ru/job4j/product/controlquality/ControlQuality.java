package ru.job4j.product.controlquality;

import ru.job4j.product.store.Store;
import ru.job4j.product.model.Food;

import java.util.List;

public class ControlQuality {
    private final List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public void examination(Food food) {
        for (Store store : storages) {
            store.add(food);
        }
    }

    public void resort(Food food) {
      examination(food);
    }
}