package ru.job4j.grabber;

import ru.job4j.grabber.utils.Post;

import java.sql.SQLException;
import java.util.List;

public interface Store extends AutoCloseable {
    void save(Post post) throws SQLException;

    List<Post> getAll() throws SQLException;

    Post findById(int id) throws SQLException;
}