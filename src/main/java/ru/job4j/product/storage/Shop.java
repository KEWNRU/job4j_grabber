package ru.job4j.product.storage;

import ru.job4j.product.store.AbstractStore;

public class Shop extends AbstractStore {

    @Override
    public String getTypeStorage() {
        return "shop";
    }
}
