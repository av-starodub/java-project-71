package hexlet.code.formatter;

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
        if ("json".equals(presentationFormat)) {
            return new JsonFormatter();
        }
        throw new IllegalArgumentException("Invalid presentationFormat: '%s'!".formatted(presentationFormat));
    }
}
