package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public final class AppTest {
    public static final String RELATIVE_PATH = "src/test/resources/fixtures";
    public static final String ABSOLUTE_PATH = Paths.get(RELATIVE_PATH).toAbsolutePath().normalize().toString();
    private String filePath1;
    private String filePath2;

    public static String createPath(String path, String fileName) {
        return String.format("%s/%s", path, fileName);
    }


    @BeforeEach
    void setUp() {
        filePath1 = createPath(ABSOLUTE_PATH, "json1.json");
        filePath2 = createPath(ABSOLUTE_PATH, "json2.json");
    }

    @Test
    void shouldFinishWithSuccessExitCode() {
        int actualExitCode = new CommandLine(new App()).execute("-f", "plain", filePath1, filePath2);
        assertThat(actualExitCode).isEqualTo(0);
    }

    @Test
    void shouldFinishWithExceptionExitCode() {
        int actualExitCode = new CommandLine(new App()).execute(filePath1, "invalid filePath");
        assertThat(actualExitCode).isEqualTo(1);
    }

    @Test
    void shouldFinishWithInputErrorCode() {
        int actualExitCode = new CommandLine(new App()).execute("invalid input");
        assertThat(actualExitCode).isEqualTo(2);
    }
}
