package ru.job4j.gc;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {
    public static void main(String[] args) {
        /* Безопасная работа с SoftReference
        * Корректным использованием безопасных ссылок является сначала получение сильной ссылки на данные, а потом работа с сильной ссылкой.
        * Это гарантирует, что в интервалах получения сильной ссылки из безопасной GC не затрет объект. */
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        System.out.println(softReference.get());
    }
}
