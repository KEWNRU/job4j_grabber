package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final String FIZZBUZZ = "FizzBuzz";
    static int startAt = 1;

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        Scanner scanner = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(validation(startAt++));
            String answer = scanner.nextLine();
            if (!answer.equals(validation(startAt))) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }

    static String validation(int number) {
        String str;
        if (number % 3 == 0 && number % 5 == 0) {
            str = FIZZBUZZ;
        } else if (number % 3 == 0) {
            str = FIZZ;
        } else if (number % 5 == 0) {
            str = BUZZ;
        } else {
            str = "";
        }
        return str.isEmpty() ? String.valueOf(number) : str;
    }
}