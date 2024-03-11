package hexlet.code.formatter.formatters.stylish;

import hexlet.code.formatter.formatters.AbstractFormatter;
import hexlet.code.difference.property.Property;

public final class StylishFormatter extends AbstractFormatter {
    private final String template;

    public StylishFormatter() {
        template = "  %s %s: %s\n";
    }

    @Override
    protected String doStart() {
        return "{\n";
    }

    @Override
    protected String doEnd() {
        return "}\n";
    }

    @Override
    protected String setAdded(Property prop) {
        return String.format(template, "+", prop.getName(), prop.getNewValue());
    }

    @Override
    protected String setDeleted(Property prop) {
        return String.format(template, "-", prop.getName(), prop.getOldValue());
    }

    @Override
    protected String setUnchanged(Property prop) {
        return String.format(template, " ", prop.getName(), prop.getOldValue());
    }

    @Override
    protected String setUpdated(Property prop) {
        return String.format("%s%s", setDeleted(prop), setAdded(prop));
    }
}
