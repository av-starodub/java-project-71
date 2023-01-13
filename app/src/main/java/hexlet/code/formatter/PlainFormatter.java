package hexlet.code.formatter;

import hexlet.code.property.Property;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter {
    @Override
    public StringBuilder format(StringBuilder sb, Property prop) {
        switch (prop.getStatus()) {
            case ADDED -> {
                return sb.append(String.format(
                        "Property '%s' was added with value: %s\n",
                        prop.getName(),
                        checkComplexValue(prop.getNewValue()))
                );
            }
            case DELETED -> {
                return sb.append(String.format("Property '%s' was removed\n", prop.getName()));
            }
            case UNCHANGED -> {
                return sb;
            }
            case UPDATED -> {
                return sb.append(String.format(
                        "Property '%s' was updated. From %s to %s\n",
                        prop.getName(),
                        checkComplexValue(prop.getOldValue()),
                        checkComplexValue(prop.getNewValue()))
                );
            }
            default -> throw new RuntimeException();
        }
    }

    private Object checkComplexValue(Object value) {
        if (value instanceof String && !"null".equals(value)) {
            return String.format("'%s'", value);
        }
        return value instanceof List<?> || value instanceof Map<?, ?> ? "[complex value]" : value;
    }
}
