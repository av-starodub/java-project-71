package hexlet.code.differ;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    public static final String RELATIVE_PATH = "src/test/resources/fixtures";
    public static final String ABSOLUTE_PATH = new File(RELATIVE_PATH).getAbsolutePath();

    private String createPath(String path, String fileName) {
        return String.format("%s/%s", path, fileName);
    }

    @Test
    public void isDifferClassExist() {
        assertThat(Differ.class).isNotNull();
    }

    @Test
    public void shouldCorrectlyGenerateTheDifferenceInStylishFormat() throws IOException {
        var expectedStylish = """
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

        String actual = Differ.generate(
                createPath(ABSOLUTE_PATH, "json1.json"),
                createPath(RELATIVE_PATH, "json2.json"));
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void shouldCorrectlyGenerateTheDifferenceInPlainFormat() throws IOException {
        var expectedPlain = """
                {
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                }""";
        String actual = Differ.generate(
                createPath(RELATIVE_PATH, "yaml1.yml"),
                createPath(ABSOLUTE_PATH, "yaml2.yml"),
                "plain");
        assertThat(actual).isEqualTo(expectedPlain);
    }
}
