package hexlet.code.builder;

import hexlet.code.formatter.StylishFormatter;
import hexlet.code.property.Property;
import hexlet.code.status.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PresentationBuilderTest {
    @Test
    public void shouldBuildChangedProperties() {
        var properties = new ArrayDeque<Property>();
        properties.addLast(Property.builder()
                .name("array")
                .status(Status.UPDATED)
                .oldValue(Arrays.asList(1, 2, 3))
                .newValue(Arrays.asList(2, 3))
                .build());
        properties.addLast(Property.builder()
                .name("object")
                .status(Status.UPDATED)
                .oldValue("{key1=10, key2=\"str\"}")
                .newValue("{key1=1}")
                .build());

        var expected = """
                {
                  - array: [1, 2, 3]
                  + array: [2, 3]
                  - object: {key1=10, key2="str"}
                  + object: {key1=1}
                }""";
        var actual = PresentationBuilder.build(properties, new StylishFormatter());

        assertThat(actual).isEqualTo(expected);
    }
}
