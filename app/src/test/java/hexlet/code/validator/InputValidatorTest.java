package hexlet.code.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class InputValidatorTest {
    @Test
    public void shouldThrowExceptionForInvalidFileExtension() {
        assertThrowsExactly(IllegalArgumentException.class, () ->
                InputValidator.checkArgs("json1", "json2", "json"));
    }

    @Test
    public void shouldThrowExceptionForUnsupportedFileExtension() {
        assertThrowsExactly(IllegalArgumentException.class, () ->
                InputValidator.checkArgs("xml1.xml", "xml2.xml", "json"));
    }

    @Test
    public void shouldThrowExceptionForUnsupportedFormat() {
        assertThrowsExactly(IllegalArgumentException.class, () ->
                InputValidator.checkArgs("json1.json", "json2.json", "yml"));
    }

    @Test
    public void shouldDoesNotThrowWithValidArguments() {
        assertDoesNotThrow(() ->
                InputValidator.checkArgs("json1.json", "json2.json", "plain"));
    }
}
