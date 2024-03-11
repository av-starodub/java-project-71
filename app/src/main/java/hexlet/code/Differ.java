package hexlet.code;

import java.io.IOException;

import hexlet.code.formatter.factory.FormatterFactory;
import hexlet.code.difference.list.SortedByNameListDiff;
import hexlet.code.parser.Parser;

public final class Differ {
    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String presentationFormat) throws IOException {
        var formatter = FormatterFactory.create(presentationFormat);
        var data1 = Parser.parseToMap(filePath1);
        var data2 =  Parser.parseToMap(filePath2);
        var listDifference = SortedByNameListDiff.create(data1, data2);

        return formatter.format(listDifference);
    }
}
