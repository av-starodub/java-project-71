package hexlet.code.parser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static hexlet.code.differ.DifferTest.ABSOLUTE_PATH;
import static hexlet.code.differ.DifferTest.RELATIVE_PATH;
import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {
    private final Map<String, Object> expectedMapAfterParse = Map.of(
            "host", "hexlet.io", "timeout", 50, "proxy", "123.234.53.22", "follow", false);

    @Test
    public void shouldCreateMapFromFlatJsonFile() throws IOException {
        var actual = Parser.parseToMap(new File(RELATIVE_PATH, "flatJson1.json"));
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expectedMapAfterParse);
    }

    @Test
    public void shouldCreateMapFromFlatYamlFile() throws IOException {
        var actual = Parser.parseToMap(new File(ABSOLUTE_PATH, "flatYaml1.yml"));
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expectedMapAfterParse);
    }
}
