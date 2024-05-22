package ru.job4j.product.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.product.controlquality.ControlQuality;
import ru.job4j.product.model.Food;
import ru.job4j.product.store.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WareHouseTest {
    @Disabled
    @Test
    void testWareHouseAdd() {
        List<Store> storages = new ArrayList<>();
        Store warehouse = new WareHouse();
        Food food = new Food("Хлеб",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 5, 20),
                10, 0, 1);
        storages.add(warehouse);
        warehouse.add(food);
        Assertions.assertEquals(food, warehouse.findBy(food));
    }
}