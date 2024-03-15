package hexlet.code.formatter;

import hexlet.code.status.Status;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, Status> mapDiff) {
        var sb = new StringBuilder();
        for (var entry : mapDiff.entrySet()) {
            var key = entry.getKey();
            var report = entry.getValue();
            var status = report.getStatus();
            switch (status) {
                case ADDED -> sb.append("Property '%s' was added with value: %s\n"
                        .formatted(key, formatValue(report.getNewValue()))
                );
                case DELETED -> sb.append("Property '%s' was removed\n".formatted(key));
                case UPDATED -> sb.append("Property '%s' was updated. From %s to %s\n"
                        .formatted(key, formatValue(report.getOldValue()), formatValue(report.getNewValue()))
                );
                case UNCHANGED -> {
                }
                default -> throw new IllegalStateException("Invalid property status: '%s'!".formatted(status));
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String formatValue(Object value) {
        if (value instanceof String) {
            return String.format("'%s'", value);
        }
        if (value instanceof Collection<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        return Objects.isNull(value) ? null : value.toString();
    }
}
