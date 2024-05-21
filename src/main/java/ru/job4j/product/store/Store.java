package ru.job4j.product.store;

import ru.job4j.product.model.Food;

public interface Store {

    void add(Food food);

    Food findBy(Food food);

    String getTypeStorage();

}
