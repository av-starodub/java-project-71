package hexlet.code.differ;

import java.io.IOException;

import hexlet.code.builder.PresentationBuilder;
import hexlet.code.formatter.factory.FormatterFactory;
import hexlet.code.parser.Parser;
import hexlet.code.service.DifferService;

public final class Differ {
    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String presentationFormat) throws IOException {
        return PresentationBuilder.build(
                DifferService.getAllSortedByName(
                        Parser.parseToMap(DifferService.getFile(filePath1)),
                        Parser.parseToMap(DifferService.getFile(filePath2))),
                FormatterFactory.create(presentationFormat)
        );
    }
}
