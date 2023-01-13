package hexlet.code.formatter;

import hexlet.code.property.Property;
import hexlet.code.status.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class StylishFormatterTest {
    private final StylishFormatter formatter = new StylishFormatter();
    @Test
    public void shouldFormatAddedProperty() {
        var prop = Property.builder()
                .name("key")
                .status(Status.ADDED)
                .newValue("new")
                .build();
        var actual = new StylishFormatter().format(new StringBuilder(), prop).toString();
        var expected = "  + key: new\n";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldFormatDeletedProperty() {
        var prop = Property.builder()
                .name("key")
                .status(Status.DELETED)
                .oldValue("old")
                .build();
        var actual = new StylishFormatter().format(new StringBuilder(), prop).toString();
        var expected = "  - key: old\n";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldFormatUnchangedProperty() {
        var prop = Property.builder()
                .name("key")
                .status(Status.UNCHANGED)
                .oldValue("old")
                .build();
        var actual = new StylishFormatter().format(new StringBuilder(), prop).toString();
        var expected = "    key: old\n";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void shouldFormatChangedProperty() {
        var prop = Property.builder()
                .name("key")
                .status(Status.CHANGED)
                .oldValue("old")
                .newValue("new")
                .build();
        var actual = formatter.format(new StringBuilder(), prop).toString();
        var expected = "  - key: old\n  + key: new\n";
        assertThat(actual).isEqualTo(expected);
    }
}