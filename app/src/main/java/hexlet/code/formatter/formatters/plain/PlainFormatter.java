package hexlet.code.formatter.formatters.plain;

import hexlet.code.formatter.formatters.AbstractFormatter;
import hexlet.code.difference.property.Property;

import java.util.List;
import java.util.Map;

public final class PlainFormatter extends AbstractFormatter {

    @Override
    protected String doStart() {
        return "";
    }

    @Override
    protected String doEnd() {
        return "";
    }

    @Override
    protected String doAdded(Property prop) {
        return String.format(
                "Property '%s' was added with value: %s\n",
                prop.getName(),
                checkComplexValue(prop.getNewValue())
        );
    }

    @Override
    protected String doDeleted(Property prop) {
        return String.format("Property '%s' was removed\n", prop.getName());
    }

    @Override
    protected String doUnchanged(Property prop) {
        return "";
    }

    @Override
    protected String doUpdated(Property prop) {
        return String.format(
                "Property '%s' was updated. From %s to %s\n",
                prop.getName(),
                checkComplexValue(prop.getOldValue()),
                checkComplexValue(prop.getNewValue())
        );
    }

    private Object checkComplexValue(Object value) {
        if (value instanceof String && !"null".equals(value)) {
            return String.format("'%s'", value);
        }
        return value instanceof List<?> || value instanceof Map<?, ?> ? "[complex value]" : value;
    }
}
