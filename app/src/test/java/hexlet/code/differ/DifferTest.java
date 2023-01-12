package hexlet.code.differ;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    public static final String RELATIVE_PATH = "src/test/resources/fixtures";
    public static final String ABSOLUTE_PATH = new File(RELATIVE_PATH).getAbsolutePath();
    private final String expectedDifference = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    private String createPath(String path, String fileName) {
        return String.format("%s/%s", path, fileName);
    }

    @Test
    public void isDifferClassExist() {
        assertThat(Differ.class).isNotNull();
    }

    @Test
    public void shouldCorrectCompareTwoJsonFiles() throws IOException {
        String actual = Differ.generate(
                createPath(ABSOLUTE_PATH, "json1.json"),
                createPath(RELATIVE_PATH, "json2.json"));
        assertThat(actual).isEqualTo(expectedDifference);
    }

    @Test
    public void shouldCorrectCompareTwoYamlFiles() throws IOException {
        String actual = Differ.generate(
                createPath(RELATIVE_PATH, "yaml1.yml"),
                createPath(ABSOLUTE_PATH, "yaml2.yml"));
        assertThat(actual).isEqualTo(expectedDifference);
    }

    @Test
    public void getUniqueKeysTest() {
        Map<String, Object> map1 = Map.of("k1", 1, "k2", "v2");
        Map<String, Object> map2 = Map.of("k1", 3, "k3", "v3");
        var actual = Differ.getUniqueKeys(map1, map2);
        assertThat(actual).containsSequence("k1", "k2", "k3");
    }
}
