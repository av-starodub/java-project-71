package hexlet.code.differ;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static final String RELATIVE_PATH = "src/test/resources/fixtures";
    private static final String ABSOLUTE_PATH = new File(RELATIVE_PATH).getAbsolutePath();
    private final Map<String, Object> expectedMapAfterParse = Map.of(
            "host", "hexlet.io", "timeout", 50, "proxy", "123.234.53.22", "follow", false);
    private final String expectedDifference = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

    private String createPath(String path, String fileName) {
        return String.format("%s/%s", path, fileName);
    }
    @Test
    public void isDifferClassExist() {
        assertThat(Differ.class).isNotNull();
    }

    @Test
    public void shouldCreateMapFromFlatJsonFile() throws IOException {
        var actual = Differ.parseToMap(new File(RELATIVE_PATH, "flatJson1.json"));
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expectedMapAfterParse);
    }

    @Test
    public void shouldCreateMapFromFlatYamlFile() throws IOException {
        var actual = Differ.parseToMap(new File(ABSOLUTE_PATH, "flatYaml1.yml"));
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expectedMapAfterParse);
    }

    @Test
    public void shouldCorrectCompareTwoFlatJsonFiles() throws IOException {
        String actual = Differ.generate(
                createPath(ABSOLUTE_PATH, "flatJson1.json"),
                createPath(RELATIVE_PATH, "flatJson2.json"));
        assertThat(actual).isEqualTo(expectedDifference);
    }

    @Test
    public void shouldCorrectCompareTwoFlatYamlFiles() throws IOException {
        String actual = Differ.generate(
                createPath(RELATIVE_PATH, "flatYaml1.yml"),
                createPath(ABSOLUTE_PATH, "flatYaml2.yml"));
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
