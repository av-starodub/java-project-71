package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import hexlet.code.formatter.FormatterFactory;
import hexlet.code.parser.Parser;

public final class Differ {
    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String presentationFormat) throws Exception {
        var formatter = FormatterFactory.create(presentationFormat);
        var data1 = Parser.parseToMap(getContent(filePath1), getDataFormat(filePath1));
        var data2 = Parser.parseToMap(getContent(filePath2), getDataFormat(filePath2));
        var mapDifference = DiffBuilder.build(data1, data2);

        return formatter.format(mapDifference);
    }

    private static String getContent(String filePath) throws IOException {
        var fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(fullPath);
    }

    private static String getDataFormat(String filePath) {
        var index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
