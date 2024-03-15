package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public final class AppTest {
    private final String filePath1 = DifferTest.JSON_FILE_PATH_1;
    private final String filePath2 = DifferTest.JSON_FILE_PATH_2;

    @Test
    void checkFinishWithSuccessExitCode() {
        int actualExitCode = new CommandLine(new App()).execute("-f", "plain", filePath1, filePath2);
        assertThat(actualExitCode).isEqualTo(0);
    }

    @Test
    void checkFinishWithExceptionExitCode() {
        int actualExitCode = new CommandLine(new App()).execute(filePath1, "invalid filePath");
        assertThat(actualExitCode).isEqualTo(1);
    }

    @Test
    void checkFinishWithInputErrorCode() {
        int actualExitCode = new CommandLine(new App()).execute("invalid input");
        assertThat(actualExitCode).isEqualTo(2);
    }
}
