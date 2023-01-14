package hexlet.code.formatter.formatters.plain;

import hexlet.code.formatter.formatters.AbstractFormatter;
import hexlet.code.property.Property;

import java.util.List;
import java.util.Map;

public final class PlainFormatter extends AbstractFormatter {

    @Override
    protected StringBuilder doAdded(StringBuilder sb, Property prop) {
        return sb.append(String.format(
                        "Property '%s' was added with value: %s\n",
                        prop.getName(),
                        checkComplexValue(prop.getNewValue())
                )
        );
    }

    @Override
    protected StringBuilder doDeleted(StringBuilder sb, Property prop) {
        return sb.append(String.format("Property '%s' was removed\n", prop.getName()));
    }

    @Override
    protected StringBuilder doUnchanged(StringBuilder sb, Property prop) {
        return sb;
    }

    @Override
    protected StringBuilder doUpdated(StringBuilder sb, Property prop) {
        return sb.append(String.format(
                        "Property '%s' was updated. From %s to %s\n",
                        prop.getName(),
                        checkComplexValue(prop.getOldValue()),
                        checkComplexValue(prop.getNewValue())
                )
        );
    }

    private Object checkComplexValue(Object value) {
        if (value instanceof String && !"null".equals(value)) {
            return String.format("'%s'", value);
        }
        return value instanceof List<?> || value instanceof Map<?, ?> ? "[complex value]" : value;
    }
}
