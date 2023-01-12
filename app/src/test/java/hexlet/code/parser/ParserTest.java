package hexlet.code.parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static hexlet.code.differ.DifferTest.ABSOLUTE_PATH;
import static hexlet.code.differ.DifferTest.RELATIVE_PATH;
import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {
    private static final Map<String, Object> EXPECTED_MAP = new HashMap<>();

    @BeforeEach
    public void setUp() {
        createExpectedMap();
    }

    @AfterEach
    public void tearDown() {
        EXPECTED_MAP.clear();
    }
    @Test
    public void shouldCreateMapFromJsonFile() throws IOException {
        var actual = Parser.parseToMap(new File(RELATIVE_PATH, "json2.json"));
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(EXPECTED_MAP);
    }

    @Test
    public void shouldCreateMapFromYamlFile() throws IOException {
        var actual = Parser.parseToMap(new File(ABSOLUTE_PATH, "yaml2.yml"));
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(EXPECTED_MAP);
    }

    private void createExpectedMap() {
        EXPECTED_MAP.put("setting1", "Another value");
        EXPECTED_MAP.put("setting2", 300);
        EXPECTED_MAP.put("setting3", "none");
        EXPECTED_MAP.put("key2", "value2");
        EXPECTED_MAP.put("numbers1", Arrays.asList(1, 2, 3, 4));
        EXPECTED_MAP.put("numbers2", Arrays.asList(22, 33, 44, 55));
        EXPECTED_MAP.put("id", null);
        EXPECTED_MAP.put("default", Arrays.asList("value1", "value2"));
        EXPECTED_MAP.put("checked", true);
        EXPECTED_MAP.put("numbers4", Arrays.asList(4, 5, 6));
        EXPECTED_MAP.put("chars1", Arrays.asList("a", "b", "c"));
        EXPECTED_MAP.put("chars2", false);
        EXPECTED_MAP.put("obj1", Map.of("nestedKey", "value", "isNested", true));
    }
}
