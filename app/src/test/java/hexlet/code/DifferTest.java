package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public final class DifferTest {
    public static final String JSON_FILE_PATH_1 = getFixturesPath("data_json_1.json").toString();
    public static final String JSON_FILE_PATH_2 = getFixturesPath("data_json_2.json").toString();
    public static final String YAML_FILE_PATH_1 = getFixturesPath("data_yaml_1.yml").toString();
    public static final String YAML_FILE_PATH_2 = getFixturesPath("data_yaml_2.yml").toString();

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturesPath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws IOException {
        var filePath = getFixturesPath(fileName);
        return Files.readString(filePath).strip();
    }

    @BeforeAll
    static void setUp() throws IOException {
        resultJson = readFixture("report_json.json");
        resultPlain = readFixture("report_plain.txt");
        resultStylish = readFixture("report_stylish.txt");
    }

    @Test
    void isDifferClassExist() {
        assertThat(Differ.class).isNotNull();
    }

    @Test
    void checkJsonDataToDefaultReport() throws Exception {
        var actualStylishReport = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2);
        assertThat(actualStylishReport).isEqualTo(resultStylish);
    }

    @Test
    void checkJsonDataToStylishReport() throws Exception {
        var actualStylishReport = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "stylish");
        assertThat(actualStylishReport).isEqualTo(resultStylish);
    }

    @Test
    void checkJsonDataToPlainReport() throws Exception {
        var actualPlainReport = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "plain");
        assertThat(actualPlainReport).isEqualTo(resultPlain);
    }

    @Test
    void checkJsonDataToJsonReport() throws Exception {
        var actualJsonReport = Differ.generate(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "json");
        assertThat(actualJsonReport).isEqualTo(resultJson);
    }

    @Test
    void checkYamlDataToDefaultReport() throws Exception {
        var actualStylishReport = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2);
        assertThat(actualStylishReport).isEqualTo(resultStylish);
    }

    @Test
    void checkYamlDataToStylishReport() throws Exception {
        var actualStylishReport = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "stylish");
        assertThat(actualStylishReport).isEqualTo(resultStylish);
    }

    @Test
    void checkYamlDataToPlainReport() throws Exception {
        String actualPlainReport = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "plain");
        assertThat(actualPlainReport).isEqualTo(resultPlain);
    }

    @Test
    void checkYamlDataToJsonReport() throws Exception {
        var actualJsonReport = Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "json");
        assertThat(actualJsonReport).isEqualTo(resultJson);
    }

    @Test
    void checkThrownWhenDataFormatNotSupported() {
        var unsupportedDataFilePath = getFixturesPath("report_stylish.txt").toString();
        var exception = assertThrowsExactly(
                IllegalArgumentException.class, () -> Differ.generate(YAML_FILE_PATH_1, unsupportedDataFilePath)
        );
        assertEquals(exception.getMessage(), "Unsupported data format : txt!");
    }

    @Test
    void checkThrownWhenPresentationFormatNotSupported() {
        var exception = assertThrowsExactly(
                IllegalArgumentException.class, () -> Differ.generate(YAML_FILE_PATH_1, YAML_FILE_PATH_2, "svg")
        );
        assertEquals(exception.getMessage(), "Unsupported presentation format : svg!");
    }
}
