package ru.job4j.grabber.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HabrCareerDateTimeParserTest {
    @Test
    public void whenGetStringThenGetCorrectLocalDateTime() throws IOException {
        DateTimeParser parser = new HabrCareerDateTimeParser();
        LocalDateTime expected = LocalDate.of(2024, 3, 25).atTime(16, 29, 22);
        LocalDateTime actual = parser.parse("2024-03-25T16:29:22+01:00");
        assertThat(actual).isEqualTo(expected);
    }

}