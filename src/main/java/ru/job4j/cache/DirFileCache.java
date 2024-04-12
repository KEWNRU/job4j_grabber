package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try {
            String file = Files.readString(Path.of(cachingDir, key));
            put(key, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return get(key);
    }
}