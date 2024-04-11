package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {
        /* безопасная работа с WeakReference
        * Корректное использование этого типа ссылок аналогично безопасным.*/
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        System.out.println(weakReference.get());
    }
}
