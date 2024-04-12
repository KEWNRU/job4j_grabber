package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь до файла");
        String directory = scanner.nextLine();
        AbstractCache<String, String> fileCache = new DirFileCache(directory);
        System.out.println("Введите имя файла или выйти");
        String file = scanner.nextLine();
        while (!"выйти".equals(file)) {
            System.out.println(fileCache.get(file));
            file = scanner.nextLine();
        }
    }
}