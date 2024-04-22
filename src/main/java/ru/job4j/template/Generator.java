package ru.job4j.template;

import java.util.Map;

public interface Generator {
    public String produce(String template, Map<String, String> args);
}