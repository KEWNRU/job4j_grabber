package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";
    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";

    public static void main(String[] args) throws IOException {
        for (int pageNumber = 1; pageNumber <= 5; pageNumber++) {
            String fullLink = "%s%s%d%s".formatted(SOURCE_LINK, PREFIX, pageNumber, SUFFIX);
            Connection connection = Jsoup.connect(fullLink);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element date = row.select(".vacancy-card__date").first().child(0);
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                String dateElement = date.attr("datetime");
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String description = retrieveDescription(link);
                System.out.printf("Добавлено: %s %s %s %n %s", dateElement, vacancyName, link, description);
            });
        }
    }

    private static String retrieveDescription(String link) {
        String description = "";
        Connection connection = Jsoup.connect(link);
        try {
            Document document = connection.get();
            Elements row = document.select(".faded-content__container");
            description = row.first().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return description;
    }
}