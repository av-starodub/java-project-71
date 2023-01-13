package hexlet.code.property;

import hexlet.code.status.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PropertyTest {
    @Test
    public void shouldCreatePropertyObject() {
        var property = Property.builder()
                .name("key")
                .status(Status.ADDED)
                .oldValue("old")
                .newValue("new")
                .build();
        assertThat(property.getName()).isEqualTo("key");
        assertThat(property.getStatus()).isEqualTo(Status.ADDED);
        assertThat(property.getOldValue()).isEqualTo("old");
        assertThat(property.getNewValue()).isEqualTo("new");
    }
}
