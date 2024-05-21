package ru.job4j.product.storage;

import ru.job4j.product.store.AbstractStore;

public class WareHouse extends AbstractStore {

    @Override
    public String getTypeStorage() {
        return "warehouse";
    }
}
