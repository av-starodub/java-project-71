package hexlet.code.formatter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
}
