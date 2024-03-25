package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    LocalDateTime created;
    int id;
    String title;
    String link;
    String description;

    public Post(LocalDateTime created, int id, String title, String link, String description) {
        this.created = created;
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post post)) {
            return false;
        }
        return id == post.id
                && Objects.equals(link, post.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    @Override
    public String toString() {
        return "Post{"
                + "created=" + created
                + ", id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
