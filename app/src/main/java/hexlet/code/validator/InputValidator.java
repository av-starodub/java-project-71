package hexlet.code.validator;

import java.util.Set;

public final class InputValidator {
    private InputValidator() {
    }

    public static void checkArgs(String filePath1, String filePath2, String format) {
        checkFileExtension(filePath1);
        checkFileExtension(filePath2);
        checkOutputFormat(format);
    }

    private static void checkFileExtension(String filePath) {
        var supported = Set.of(".json", ".yml");

        var dotIndex = filePath.lastIndexOf(".");
        if (dotIndex > 0) {
            var extension = filePath.substring(filePath.lastIndexOf("."));
            if (supported.contains(extension)) {
                return;
            }
        }
        throw new IllegalArgumentException("Unsupported/invalid file extension: " + filePath);
    }

    private static void checkOutputFormat(String format) {
        var supported = Set.of("stylish", "plain", "json");
        if (!supported.contains(format)) {
            throw new IllegalArgumentException("Unsupported/invalid output format: " + format);
        }
    }
}
