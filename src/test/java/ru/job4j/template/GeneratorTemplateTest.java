package ru.job4j.template;

import org.assertj.core.api.StringAssert;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Disabled
public class GeneratorTemplateTest {
    @Test
    public void the_map_has_a_key_and_a_value() {
        GeneratorTemplate generatorTemplate = new GeneratorTemplate();
        String template = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan");
        String expected = "I am a Ivan";
        assertThat(generatorTemplate.produce(template, map)).isEqualTo(expected);
    }

    @Test
    public void The_template_contains_keys_that_are_not_in_the_map() {
        GeneratorTemplate generatorTemplate = new GeneratorTemplate();
        String template = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", null);
        assertThatThrownBy(() -> generatorTemplate.produce(template, map)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void there_is_an_extra_key_in_the_card() {
        GeneratorTemplate generatorTemplate = new GeneratorTemplate();
        String template = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan");
        map.put("subject", "job4j");
        assertThatThrownBy(() -> generatorTemplate.produce(template, map)).isInstanceOf(IllegalArgumentException.class);
    }
}