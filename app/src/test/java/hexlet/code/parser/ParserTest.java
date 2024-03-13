package hexlet.code.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class ParserTest {
    @Test
    void shouldThrowWhenDataFormatIsInvalid() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Parser.parseToMap("content", ""));
    }
}
