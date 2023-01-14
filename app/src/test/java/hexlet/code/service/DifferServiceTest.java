package hexlet.code.service;

import hexlet.code.differ.DifferTest;
import hexlet.code.property.Property;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferServiceTest {
    @Test
    public void shouldCreateListOfPropertiesSortedByName() {
        var property1 = Property.builder()
                .name("a")
                .oldValue("str")
                .newValue("")
                .build();
        var property2 = Property.builder()
                .name("g")
                .oldValue(1)
                .newValue(5)
                .build();
        var property3 = Property.builder()
                .name("s")
                .oldValue(null)
                .newValue(false)
                .build();
        var property4 = Property.builder()
                .name("v")
                .oldValue(false)
                .newValue(null)
                .build();

        Map<String, Object> data1 = Map.of("g", 1, "v", false, "a", "str");
        Map<String, Object> data2 = Map.of("g", 5, "s", false, "a", "");

        var properties = DifferService.getAllSortedByName(data1, data2);
        var firstPropertyName = properties.get(0).getName();
        var secondPropertyName = properties.get(1).getName();
        var thirdPropertyName = properties.get(2).getName();
        var fourthPropertyName = properties.get(3).getName();

        assertThat(firstPropertyName).isEqualTo(property1.getName());
        assertThat(secondPropertyName).isEqualTo(property2.getName());
        assertThat(thirdPropertyName).isEqualTo(property3.getName());
        assertThat(fourthPropertyName).isEqualTo(property4.getName());
    }

    @Test
    public void shouldCreateFileFromAbsolutePath() {
        var absolutePath = DifferTest.ABSOLUTE_PATH + "/" + "json1.json";
        var file = DifferService.getFile(absolutePath);
        assertThat(file).isFile();
    }
    @Test
    public void shouldCreateFileFromRelativePath() {
        var absolutePath = DifferTest.RELATIVE_PATH + "/" + "json1.json";
        var file = DifferService.getFile(absolutePath);
        assertThat(file).isFile();
    }
}
