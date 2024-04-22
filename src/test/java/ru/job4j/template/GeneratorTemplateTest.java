package ru.job4j.template;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Disabled

public class GeneratorTemplateTest {
    @Test
    public void theMapHasAKeyAndAValue() {
        GeneratorTemplate generatorTemplate = new GeneratorTemplate();
        String template = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan");
        String expected = "I am a Ivan";
        assertThat(generatorTemplate.produce(template, map)).isEqualTo(expected);
    }

    @Test
    public void theTemplateContainsKeysThatAreNotInTheMap() {
        GeneratorTemplate generatorTemplate = new GeneratorTemplate();
        String template = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", null);
        assertThatThrownBy(() -> generatorTemplate.produce(template, map)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void thereIsAnExtraKeyInTheCard() {
        GeneratorTemplate generatorTemplate = new GeneratorTemplate();
        String template = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan");
        map.put("subject", "job4j");
        assertThatThrownBy(() -> generatorTemplate.produce(template, map)).isInstanceOf(IllegalArgumentException.class);
    }
}