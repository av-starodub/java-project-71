package hexlet.code.formatter;

public final class FormatterFactory {
    private FormatterFactory() {
    }

    public static Formatter create(String presentationFormat) {
        if ("plain".equals(presentationFormat)) {
            return new PlainFormatter();
        }
        if ("json".equals(presentationFormat)) {
            return new JsonFormatter();
        }
        return new StylishFormatter();
    }
}
