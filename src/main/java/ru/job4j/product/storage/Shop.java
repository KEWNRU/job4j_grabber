package ru.job4j.product.storage;

import ru.job4j.product.controlquality.Remainder;
import ru.job4j.product.model.Food;
import ru.job4j.product.store.AbstractStore;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    private final List<Food> shop = new ArrayList<>();

    @Override
    public void add(Food food) {
        Remainder remainder = new Remainder();
        double r = remainder.getRemainderExpiryDate(food);
        if (r >= 25 && r < 75) {
            shop.add(food);
        }
        if (r <= 25) {
            food.sale();
            shop.add(food);
        }
    }

    @Override
    public Food findBy(Food food) {
        for (Food f : shop) {
            if (f.getId() == food.getId()) {
                return f;
            }
        }
        return null;
    }

    @Override
    public String getTypeStorage() {
        return "shop";
    }
}
