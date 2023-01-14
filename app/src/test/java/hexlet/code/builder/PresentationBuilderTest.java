package hexlet.code.builder;

import hexlet.code.formatter.formatters.plain.PlainFormatter;
import hexlet.code.formatter.formatters.stylish.StylishFormatter;
import hexlet.code.property.Property;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PresentationBuilderTest {
    @Test
    public void shouldBuildPropertiesToStylishFormat() {
        var properties = new ArrayList<Property>();
        properties.add(Property.builder()
                .name("array")
                .oldValue(Arrays.asList(1, 2, 3))
                .newValue(Arrays.asList(2, 3))
                .build());
        properties.add(Property.builder()
                .name("object")
                .oldValue(Map.of("key1", 10))
                .newValue(Map.of("key1", 100))
                .build());

        var expected = """
                {
                  - array: [1, 2, 3]
                  + array: [2, 3]
                  - object: {key1=10}
                  + object: {key1=100}
                }""";
        var actual = PresentationBuilder.build(properties, new StylishFormatter());

        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldBuildPropertiesToPlainFormat() {
        var properties = new ArrayList<Property>();
        properties.add(Property.builder()
                .name("array")
                .oldValue(Arrays.asList(1, 2, 3))
                .newValue(Arrays.asList(2, 3))
                .build());
        properties.add(Property.builder()
                .name("object")
                .oldValue(Map.of("key1", 10))
                .newValue("null")
                .build());

        var expected = """
                {
                Property 'array' was updated. From [complex value] to [complex value]
                Property 'object' was updated. From [complex value] to null
                }""";
        var actual = PresentationBuilder.build(properties, new PlainFormatter());

        assertThat(actual).isEqualTo(expected);
    }
}
