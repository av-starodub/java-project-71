package hexlet.code.builder;

import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.property.Property;
import hexlet.code.status.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PresentationBuilderTest {
    @Test
    public void shouldBuildPropertiesToStylishFormat() {
        var properties = new ArrayDeque<Property>();
        properties.addLast(Property.builder()
                .name("array")
                .status(Status.UPDATED)
                .oldValue(Arrays.asList(1, 2, 3))
                .newValue(Arrays.asList(2, 3))
                .build());
        properties.addLast(Property.builder()
                .name("object")
                .status(Status.UPDATED)
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
        var properties = new ArrayDeque<Property>();
        properties.addLast(Property.builder()
                .name("array")
                .status(Status.UPDATED)
                .oldValue(Arrays.asList(1, 2, 3))
                .newValue(Arrays.asList(2, 3))
                .build());
        properties.addLast(Property.builder()
                .name("object")
                .status(Status.UPDATED)
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
