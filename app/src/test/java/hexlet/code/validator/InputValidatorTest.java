package hexlet.code.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class InputValidatorTest {
    @Test
    void shouldThrowExceptionForInvalidFileExtension() {
        assertThrowsExactly(IllegalArgumentException.class, () ->
                InputValidator.checkArgs("json1", "json2", "json"));
    }

    @Test
    void shouldThrowExceptionForUnsupportedFileExtension() {
        assertThrowsExactly(IllegalArgumentException.class, () ->
                InputValidator.checkArgs("xml1.xml", "xml2.xml", "json"));
    }

    @Test
    void shouldThrowExceptionForUnsupportedFormat() {
        assertThrowsExactly(IllegalArgumentException.class, () ->
                InputValidator.checkArgs("json1.json", "json2.json", "yml"));
    }

    @Test
    void shouldDoesNotThrowWithValidArguments() {
        assertDoesNotThrow(() ->
                InputValidator.checkArgs("json1.json", "json2.json", "plain"));
    }
}
