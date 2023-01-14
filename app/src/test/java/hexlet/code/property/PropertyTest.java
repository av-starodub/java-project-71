package hexlet.code.property;

import hexlet.code.status.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PropertyTest {
    @Test
    public void shouldCreatePropertyObject() {
        var property = Property.builder()
                .name("key")
                .oldValue("old")
                .newValue("new")
                .build();
        assertThat(property).isExactlyInstanceOf(Property.class);
        assertThat(property.getName()).isEqualTo("key");
        assertThat(property.getStatus()).isEqualTo(Status.UPDATED);
        assertThat(property.getOldValue()).isEqualTo("old");
        assertThat(property.getNewValue()).isEqualTo("new");
    }

    @Test
    public void shouldSetAddedAsStatusOfProperty() {
        var property = Property.builder()
                .name("key")
                .oldValue(null)
                .newValue("new")
                .build();
        assertThat(property.getStatus()).isEqualTo(Status.ADDED);
    }

    @Test
    public void shouldSetDeletedAsStatusOfProperty() {
        var property = Property.builder()
                .name("key")
                .oldValue("old")
                .newValue(null)
                .build();
        assertThat(property.getStatus()).isEqualTo(Status.DELETED);
    }

    @Test
    public void shouldSetUnchangedAsStatusOfProperty() {
        var property = Property.builder()
                .name("key")
                .oldValue("old")
                .newValue("old")
                .build();
        assertThat(property.getStatus()).isEqualTo(Status.UNCHANGED);
    }
}
