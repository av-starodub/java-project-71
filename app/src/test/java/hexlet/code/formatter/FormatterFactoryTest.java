package hexlet.code.formatter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class FormatterFactoryTest {
    @Test
    void shouldCreateStylishFormatter() {
        var actual = FormatterFactory.create("stylish");
        assertThat(actual).isExactlyInstanceOf(StylishFormatter.class);
    }

    @Test
    void shouldCreatePlainFormatter() {
        var actual = FormatterFactory.create("plain");
        assertThat(actual).isExactlyInstanceOf(PlainFormatter.class);
    }

    @Test
    void shouldCreateJsonFormatter() {
        var actual = FormatterFactory.create("json");
        assertThat(actual).isExactlyInstanceOf(JsonFormatter.class);
    }

    @Test
    void shouldThrowWhenFormatUnsupported() {
        assertThrowsExactly(IllegalArgumentException.class, () -> FormatterFactory.create("invalid"));
    }
}
