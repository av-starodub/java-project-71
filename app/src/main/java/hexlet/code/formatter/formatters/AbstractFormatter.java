package hexlet.code.formatter.formatters;

import hexlet.code.formatter.Formatter;
import hexlet.code.property.Property;

public abstract class AbstractFormatter implements Formatter {
    @Override
    public StringBuilder format(StringBuilder sb, Property prop) {
        switch (prop.getStatus()) {
            case ADDED -> {
                return doAdded(sb, prop);
            }
            case DELETED -> {
                return doDeleted(sb, prop);
            }
            case UNCHANGED -> {
                return doUnchanged(sb, prop);
            }
            case UPDATED -> {
                return doUpdated(sb, prop);
            }
            default -> throw new RuntimeException();
        }
    }

    protected abstract StringBuilder doAdded(StringBuilder sb, Property prop);

    protected abstract StringBuilder doDeleted(StringBuilder sb, Property prop);

    protected abstract StringBuilder doUnchanged(StringBuilder sb, Property prop);

    protected abstract StringBuilder doUpdated(StringBuilder sb, Property prop);
}
