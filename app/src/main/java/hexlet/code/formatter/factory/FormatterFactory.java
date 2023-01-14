package hexlet.code.formatter.factory;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.formatters.plain.PlainFormatter;
import hexlet.code.formatter.formatters.stylish.StylishFormatter;

public final class FormatterFactory {
    private FormatterFactory() {
    }
    public static Formatter create(String presentationFormat) {
        if ("stylish".equals(presentationFormat)) {
            return new StylishFormatter();
        }
        if ("plain".equals(presentationFormat)) {
            return new PlainFormatter();
        }
        throw new RuntimeException();
    }
}
