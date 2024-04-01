package ru.job4j.grabber;

import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;
import ru.job4j.grabber.utils.Post;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

public class PsqlStore implements Store {

    private Connection connection;

    public PsqlStore(Properties config) throws SQLException, ClassNotFoundException {
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Post post) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO post (name, text, link, created) values (?, ?, ?, ?) ON CONFLICT(link) DO NOTHING;",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.executeUpdate();
        }
    }

    @Override
    public List<Post> getAll() throws SQLException {
        List<Post> rsl = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post;")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                rsl.add(new Post(rs.getTimestamp("created").toLocalDateTime(),
                        rs.getString("name"),
                        rs.getString("link"),
                        rs.getString("text")));
            }
        }
        return rsl;
    }

    @Override
    public Post findById(int id) throws SQLException {
        Post rsl = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post where id = ?;")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                rsl = new Post(rs.getTimestamp("created").toLocalDateTime(),
                        rs.getString("name"),
                        rs.getString("link"),
                        rs.getString("text"));
            }
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        HabrCareerParse parse = new HabrCareerParse(new HabrCareerDateTimeParser());
        PsqlStore psqlStore = new PsqlStore(properties);
        List<Post> con = parse.list("https://career.habr.com/vacancies?page=&q=Java+developer&type=all");
        for (Post post : con) {
            psqlStore.save(post);
        }
        System.out.println(psqlStore.getAll());

    }
}