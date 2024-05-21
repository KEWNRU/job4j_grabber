package ru.job4j.product.controlquality;

import ru.job4j.product.store.Store;
import ru.job4j.product.model.Food;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private final List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public double getRemainderExpiryDate(Food food) {
        LocalDate expiryDate = food.getExpiryDate();
        LocalDate createDate = food.getCreateDate();
        LocalDate now = LocalDate.now();
        double ex = expiryDate.getDayOfYear() - createDate.getDayOfYear();
        double cr = expiryDate.getDayOfYear() - now.getDayOfYear();
        return (cr / ex) * 100;
    }

    public void examination(Food food) {
        double remainderExpiryDate = getRemainderExpiryDate(food);
        for (Store store : storages) {
            if (remainderExpiryDate > 25 && store.getTypeStorage().equals("warehouse")) {
                store.add(food);
            }
            if (remainderExpiryDate >= 25 && remainderExpiryDate < 75
                    && store.getTypeStorage().equals("shop")) {
                store.add(food);
            }
            if (remainderExpiryDate < 75 && store.getTypeStorage().equals("shop")) {
                food.sale();
                store.add(food);
            }
            if (remainderExpiryDate == 0 && store.getTypeStorage().equals("trash")) {
                store.add(food);
            }
        }
    }
}