package hexlet.code.formatter.formatters.stylish;

import hexlet.code.formatter.formatters.AbstractFormatter;
import hexlet.code.property.Property;

public final class StylishFormatter extends AbstractFormatter {
    private final String template;

    public StylishFormatter() {
        template = "  %s %s: %s\n";
    }

    @Override
    protected StringBuilder doAdded(StringBuilder sb, Property prop) {
        return sb.append(String.format(template, "+", prop.getName(), prop.getNewValue()));
    }

    @Override
    protected StringBuilder doDeleted(StringBuilder sb, Property prop) {
        return sb.append(String.format(template, "-", prop.getName(), prop.getOldValue()));
    }

    @Override
    protected StringBuilder doUnchanged(StringBuilder sb, Property prop) {
        return sb.append(String.format(template, " ", prop.getName(), prop.getOldValue()));
    }

    @Override
    protected StringBuilder doUpdated(StringBuilder sb, Property prop) {
        return sb
                .append(String.format(template, "-", prop.getName(), prop.getOldValue()))
                .append(String.format(template, "+", prop.getName(), prop.getNewValue()));
    }
}
