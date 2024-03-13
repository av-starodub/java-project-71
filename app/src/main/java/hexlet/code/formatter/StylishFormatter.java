package hexlet.code.formatter;

import hexlet.code.property.Property;

import java.util.List;

public final class StylishFormatter implements Formatter {
    private static final String TEMPLATE = "  %s %s: %s\n";

    @Override
    public String format(List<Property> listDiff) {
        var sb = new StringBuilder();
        sb.append("{\n");
        for (var prop : listDiff) {
            var status = prop.getStatus();
            switch (status) {
                case ADDED -> sb.append(formatProperty("+", prop));
                case DELETED -> sb.append(formatProperty("-", prop));
                case UNCHANGED -> sb.append(formatProperty(" ", prop));
                case UPDATED -> {
                    sb.append(formatProperty("-", prop));
                    sb.append(formatProperty("+", prop));
                }
                default -> throw new IllegalStateException("Invalid property status: '%s'!".formatted(status));
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private String formatProperty(String prefix, Property property) {
        var value = "+".equals(prefix) ? property.getNewValue() : property.getOldValue();
        return TEMPLATE.formatted(prefix, property.getName(), value);
    }
}
