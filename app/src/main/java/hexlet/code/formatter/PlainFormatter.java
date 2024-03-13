package hexlet.code.formatter;

import hexlet.code.property.Property;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(List<Property> listDiff) {
        var sb = new StringBuilder();
        for (var prop : listDiff) {
            var status = prop.getStatus();
            switch (status) {
                case ADDED -> sb.append("Property '%s' was added with value: %s\n"
                        .formatted(prop.getName(), formatValue(prop.getNewValue()))
                );
                case UPDATED -> sb.append("Property '%s' was updated. From %s to %s\n"
                        .formatted(prop.getName(), formatValue(prop.getOldValue()), formatValue(prop.getNewValue()))
                );
                case DELETED -> sb.append("Property '%s' was removed\n".formatted(prop.getName()));
                case UNCHANGED -> {
                }
                default -> throw new IllegalStateException("Invalid property status: '%s'!".formatted(status));
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String formatValue(Object value) {
        if (value instanceof String && !"null".equals(value)) {
            return String.format("'%s'", value);
        }
        return value instanceof List<?> || value instanceof Map<?, ?> ? "[complex value]" : value.toString();
    }
}
