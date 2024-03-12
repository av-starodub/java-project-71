package hexlet.code.formatter.formatters;

import hexlet.code.formatter.Formatter;
import hexlet.code.property.Property;

import java.util.List;

public abstract class AbstractFormatter implements Formatter {
    @Override
    public final String format(List<Property> listDiff) {
        var sb = new StringBuilder();
        sb.append(doStart());
        for (var prop : listDiff) {
            switch (prop.getStatus()) {
                case ADDED -> sb.append(setAdded(prop));
                case DELETED -> sb.append(setDeleted(prop));
                case UNCHANGED -> sb.append(setUnchanged(prop));
                case UPDATED -> sb.append(setUpdated(prop));
                default -> { }
            }
        }
        sb.append(doEnd());
        return sb.substring(0, sb.length() - 1);
    }

    protected abstract String doStart();

    protected abstract String doEnd();

    protected abstract String setAdded(Property prop);

    protected abstract String setDeleted(Property prop);

    protected abstract String setUnchanged(Property prop);

    protected abstract String setUpdated(Property prop);
}
