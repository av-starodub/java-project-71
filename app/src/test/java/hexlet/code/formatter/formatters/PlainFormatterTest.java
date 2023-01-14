package hexlet.code.formatter.formatters;

import hexlet.code.formatter.formatters.plain.PlainFormatter;
import hexlet.code.property.Property;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlainFormatterTest {
    @Test
    public void shouldFormatAddedProperty() {
        var prop = Property.builder()
                .name("key")
                .newValue(Arrays.asList(1, 2, 3))
                .build();
        var actual = new PlainFormatter().format(new StringBuilder(), prop).toString();
        var expected = "Property 'key' was added with value: [complex value]\n";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldFormatDeletedProperty() {
        var prop = Property.builder()
                .name("key")
                .oldValue("old")
                .build();
        var actual = new PlainFormatter().format(new StringBuilder(), prop).toString();
        var expected = "Property 'key' was removed\n";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldFormatUnchangedProperty() {
        var prop = Property.builder()
                .name("key")
                .oldValue("old")
                .newValue("old")
                .build();
        var actual = new PlainFormatter().format(new StringBuilder(), prop).toString();
        var expected = "";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldFormatChangedProperty() {
        var prop = Property.builder()
                .name("key")
                .oldValue("none")
                .newValue(true)
                .build();
        var actual = new PlainFormatter().format(new StringBuilder(), prop).toString();
        var expected = "Property 'key' was updated. From 'none' to true\n";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldFormatChangedPropertyWithNull() {
        var prop = Property.builder()
                .name("key")
                .oldValue("null")
                .newValue(Map.of("k1", "v1"))
                .build();
        var actual = new PlainFormatter().format(new StringBuilder(), prop).toString();
        var expected = "Property 'key' was updated. From null to [complex value]\n";
        assertThat(actual).isEqualTo(expected);
    }
}
