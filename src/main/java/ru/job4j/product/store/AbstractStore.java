package ru.job4j.product.store;

import ru.job4j.product.model.Food;

import java.util.ArrayList;
import java.util.List;

public class AbstractStore implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public Food findBy(Food food) {
        for (Food f : foods) {
            if (f.getId() == food.getId()) {
                return f;
            }
        }
        return null;
    }

    @Override
    public String getTypeStorage() {
        return "";
    }
}
