package hexlet.code.differ;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static final String RESOURCES_FIXTURES = new File("src/test/resources/fixtures").getAbsolutePath();

    private String getTestFile(String fileName) {
        return String.format("%s/%s", RESOURCES_FIXTURES, fileName);
    }

    @Test
    public void shouldCorrectToShowDifferenceBetweenTwoFlatJsonFiles() throws IOException {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(getTestFile("flatJson1.json"), getTestFile("flatJson2.json"));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getUniqueKeysTest() {
        Map<String, Object> map1 = Map.of("k1", 1, "k2", "v2");
        Map<String, Object> map2 = Map.of("k1", 3, "k3", "v3");
        var actual = Differ.getUniqueKeys(map1, map2);
        assertThat(actual).containsSequence("k1", "k2", "k3");
    }
}
