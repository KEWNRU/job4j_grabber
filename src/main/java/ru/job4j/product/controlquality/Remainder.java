package ru.job4j.product.controlquality;

import ru.job4j.product.model.Food;

import java.time.LocalDate;

public class Remainder {

    public double getRemainderExpiryDate(Food food) {
        LocalDate expiryDate = food.getExpiryDate();
        LocalDate createDate = food.getCreateDate();
        LocalDate now = LocalDate.now();
        double ex = expiryDate.getDayOfYear() - createDate.getDayOfYear();
        double cr = expiryDate.getDayOfYear() - now.getDayOfYear();
        return (cr / ex) * 100;
    }
}
