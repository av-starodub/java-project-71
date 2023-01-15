package hexlet.code.formatter.formatters.stylish;

import hexlet.code.formatter.formatters.AbstractFormatter;
import hexlet.code.property.Property;

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
        return "}";
    }

    @Override
    protected String doAdded(Property prop) {
        return String.format(template, "+", prop.getName(), prop.getNewValue());
    }

    @Override
    protected String doDeleted(Property prop) {
        return String.format(template, "-", prop.getName(), prop.getOldValue());
    }

    @Override
    protected String doUnchanged(Property prop) {
        return String.format(template, " ", prop.getName(), prop.getOldValue());
    }

    @Override
    protected String doUpdated(Property prop) {
        return String.format("%s%s", doDeleted(prop), doAdded(prop));
    }
}
