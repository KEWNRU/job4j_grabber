package ru.job4j.kiss.fool;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FoolTest {

    @Test
    public void testFizz() {
        assertEquals("Fizz", Fool.validation(3));
        assertEquals("Fizz", Fool.validation(6));
    }

    @Test
    public void testBuzz() {
        assertEquals("Buzz", Fool.validation(5));
        assertEquals("Buzz", Fool.validation(10));
    }

    @Test
    public void testFizzBuzz() {
        assertEquals("FizzBuzz", Fool.validation(15));
        assertEquals("FizzBuzz", Fool.validation(30));
    }

    @Test
    public void testNumber() {
        assertEquals("1", Fool.validation(1));
        assertEquals("2", Fool.validation(2));
    }
}