package ru.job4j.product.storage;

import ru.job4j.product.controlquality.Remainder;
import ru.job4j.product.model.Food;
import ru.job4j.product.store.AbstractStore;

import java.util.ArrayList;
import java.util.List;

public class WareHouse extends AbstractStore {

    private final List<Food> warehouse = new ArrayList<>();

    @Override
    public void add(Food food) {
        Remainder remainder = new Remainder();
        double r = remainder.getRemainderExpiryDate(food);
        if (r > 75) {
            warehouse.add(food);
        }
    }

    @Override
    public Food findBy(Food food) {
        for (Food f : warehouse) {
            if (f.getId() == food.getId()) {
                return f;
            }
        }
        return null;
    }

    @Override
    public String getTypeStorage() {
        return "warehouse";
    }
}
