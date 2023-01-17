package hexlet.code.formatter.formatters;

import hexlet.code.formatter.Formatter;
import hexlet.code.list.SortedByNameListDiff;
import hexlet.code.property.Property;

public abstract class AbstractFormatter implements Formatter {
    @Override
    public String format(SortedByNameListDiff listDiff) {
        var sb = new StringBuilder();
        sb.append(doStart());
        for (var prop : listDiff.getAll()) {
            switch (prop.getStatus()) {
                case ADDED -> sb.append(doAdded(prop));
                case DELETED -> sb.append(doDeleted(prop));
                case UNCHANGED -> sb.append(doUnchanged(prop));
                case UPDATED -> sb.append(doUpdated(prop));
                default -> { }
            }
        }
        sb.append(doEnd());
        return sb.substring(0, sb.length() - 1);
    }

    protected abstract String doStart();

    protected abstract String doEnd();

    protected abstract String doAdded(Property prop);

    protected abstract String doDeleted(Property prop);

    protected abstract String doUnchanged(Property prop);

    protected abstract String doUpdated(Property prop);
}
