package ru.job4j.product.controlquality;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.product.storage.Shop;
import ru.job4j.product.storage.Trash;
import ru.job4j.product.store.Store;
import ru.job4j.product.model.Food;
import ru.job4j.product.storage.WareHouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.Assert.assertEquals;

@Disabled
public class ControlQualityTest {
    @Test
    public void testGetRemainderExpiryDate() {
        Food food = new Food("яблоко",
                LocalDate.of(2024, 5, 30),
                LocalDate.of(2024, 5, 11),
                10, 0, 1);
        Remainder remainder = new Remainder();
        double result = remainder.getRemainderExpiryDate(food);
        Assertions.assertEquals(42.1, result, 0.01);
    }

    @Test
    public void testExaminationShop() {
        List<Store> storages = new ArrayList<>();
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food food = new Food("Хлеб",
                LocalDate.of(2024, 5, 23),
                LocalDate.of(2024, 5, 20),
                10, 0, 1);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.examination(food);
        Assertions.assertEquals(food, shop.findBy(food));
    }

    @Test
    public void testExaminationShopDiscount() {
        List<Store> storages = new ArrayList<>();
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food food = new Food("Хлеб",
                LocalDate.of(2024, 5, 22),
                LocalDate.of(2024, 5, 15),
                10, 20, 1);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.examination(food);
        Assertions.assertEquals(8, shop.findBy(food).getPrice());
    }

    @Test
    public void testExaminationShopTrash() {
        List<Store> storages = new ArrayList<>();
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food food = new Food("Хлеб",
                LocalDate.of(2024, 5, 22),
                LocalDate.of(2024, 5, 10),
                10, 0, 1);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.examination(food);
        Assertions.assertEquals(food, trash.findBy(food));
    }

    @Test
    public void testExaminationWarehouseAndNull() {
        List<Store> storages = new ArrayList<>();
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food food = new Food("Хлеб",
                LocalDate.of(2024, 6, 20),
                LocalDate.of(2024, 5, 20),
                10, 0, 1);
        Food food1 = new Food("Хлеб",
                LocalDate.of(2024, 5, 21),
                LocalDate.of(2024, 5, 20),
                10, 0, 2);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.examination(food);
        controlQuality.examination(food1);
        Assertions.assertEquals(food, warehouse.findBy(food));
        Assertions.assertNull(warehouse.findBy(food1));
    }

    @Test
    public void testRestorTrash() {
        List<Store> storages = new ArrayList<>();
        Store warehouse = new WareHouse();
        Store shop = new Shop();
        Store trash = new Trash();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food food = new Food("Хлеб",
                LocalDate.of(2024, 5, 20),
                LocalDate.of(2024, 5, 20),
                10, 0, 1);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.resort(food);
        Assertions.assertEquals(food, trash.findBy(food));
    }
}
