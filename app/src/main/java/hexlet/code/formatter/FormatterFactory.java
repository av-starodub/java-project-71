package hexlet.code.formatter;

public final class FormatterFactory {
    private FormatterFactory() {
    }

    public static Formatter create(String presentationFormat) {
        switch (presentationFormat) {
            case "stylish" -> {
                return new StylishFormatter();
            }
            case "plain" -> {
                return new PlainFormatter();
            }
            case "json" -> {
                return new JsonFormatter();
            }
            default -> throw new IllegalArgumentException(
                    "Invalid presentationFormat: '%s'!".formatted(presentationFormat)
            );
        }
    }
}
