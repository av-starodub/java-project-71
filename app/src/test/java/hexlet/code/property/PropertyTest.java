package hexlet.code.property;

import hexlet.code.difference.property.Property;
import hexlet.code.difference.property.PropertyStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PropertyTest {
    @Test
    void shouldCreatePropertyObject() {
        var property = Property.builder()
                .name("key")
                .oldValue("old")
                .newValue("new")
                .build();
        assertThat(property).isExactlyInstanceOf(Property.class);
        assertThat(property.getName()).isEqualTo("key");
        assertThat(property.getStatus()).isEqualTo(PropertyStatus.UPDATED);
        assertThat(property.getOldValue()).isEqualTo("old");
        assertThat(property.getNewValue()).isEqualTo("new");
    }

    @Test
    void shouldSetAddedAsStatusOfProperty() {
        var property = Property.builder()
                .name("key")
                .oldValue(null)
                .newValue("new")
                .build();
        assertThat(property.getStatus()).isEqualTo(PropertyStatus.ADDED);
    }

    @Test
    void shouldSetDeletedAsStatusOfProperty() {
        var property = Property.builder()
                .name("key")
                .oldValue("old")
                .newValue(null)
                .build();
        assertThat(property.getStatus()).isEqualTo(PropertyStatus.DELETED);
    }

    @Test
    void shouldSetUnchangedAsStatusOfProperty() {
        var property = Property.builder()
                .name("key")
                .oldValue("old")
                .newValue("old")
                .build();
        assertThat(property.getStatus()).isEqualTo(PropertyStatus.UNCHANGED);
    }
}
