package hexlet.code.formatter;

import hexlet.code.property.Property;

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
    protected String setAdded(Property prop) {
        return String.format(
                "Property '%s' was added with value: %s\n",
                prop.getName(),
                checkComplexValue(prop.getNewValue())
        );
    }

    @Override
    protected String setDeleted(Property prop) {
        return String.format("Property '%s' was removed\n", prop.getName());
    }

    @Override
    protected String setUnchanged(Property prop) {
        return "";
    }

    @Override
    protected String setUpdated(Property prop) {
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
