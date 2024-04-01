package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;
import ru.job4j.grabber.utils.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";
    public static final int PAGE = 5;

    private static DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> lists = new ArrayList<>();
        for (int i = 1; i <= PAGE; i++) {
            Connection connection = Jsoup.connect(link);
            try {
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                for (Element row : rows) {
                    Element titleElement = row.select(".vacancy-card__title").first();
                    Element linkElement = titleElement.child(0);
                    Element date = row.select(".vacancy-card__date").first().child(0);
                    String vacancyName = titleElement.text();
                    String dateElement = date.attr("datetime");
                    String link1 = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                    String description = retrieveDescription(link1);
                    lists.add(new Post(dateTimeParser.parse(dateElement), vacancyName, link1, description));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return lists;
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