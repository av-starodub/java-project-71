package hexlet.code.builder;

import hexlet.code.formatter.Formatter;
import hexlet.code.property.Property;

import java.util.ArrayDeque;

public final class PresentationBuilder {
    private PresentationBuilder() {
    }

    public static String build(ArrayDeque<Property> properties, Formatter formatter) {
        var difference = new StringBuilder();
        difference.append("{\n");
        properties.forEach(property -> formatter.format(difference, property));
        difference.append("}");
        return difference.toString();
    }
}
