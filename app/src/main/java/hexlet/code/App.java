package hexlet.code;

import hexlet.code.differ.Differ;
import hexlet.code.validator.InputValidator;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Objects;
import java.util.concurrent.Callable;

@Command(name = "gendiff [-hV]", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int EXCEPTION_EXIT_CODE = 1;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() {
        try {
            var presentationFormat = Objects.isNull(format) ? "stylish" : format;
            InputValidator.checkArgs(filePath1, filePath2, presentationFormat);
            showDifference(Differ.generate(filePath1, filePath2, presentationFormat));
        } catch (Throwable e) {
            printExceptionInfo(e);
            return EXCEPTION_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }

    private static void showDifference(String diff) {
        System.out.println(diff);
    }

    private static void printExceptionInfo(Throwable e) {
        System.out.printf("%s: %s%n%s%n", e.getClass(), e.getMessage(), e.getStackTrace()[0]);
    }
}
