package hexlet.code.formatter;

import hexlet.code.type.StatusType;
import hexlet.code.type.ReportType;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.type.ReportType.NEW_VALUE;
import static hexlet.code.type.ReportType.OLD_VALUE;
import static hexlet.code.type.ReportType.STATUS;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, EnumMap<ReportType, Object>> listDiff) {
        var sb = new StringBuilder();
        for (var entry : listDiff.entrySet()) {
            var key = entry.getKey();
            var report = entry.getValue();
            var status = (StatusType) report.get(STATUS);
            switch (status) {
                case ADDED -> sb.append("Property '%s' was added with value: %s\n"
                        .formatted(key, formatValue(report.get(NEW_VALUE)))
                );
                case DELETED -> sb.append("Property '%s' was removed\n".formatted(key));
                case UPDATED -> sb.append("Property '%s' was updated. From %s to %s\n"
                        .formatted(key, formatValue(report.get(OLD_VALUE)), formatValue(report.get(NEW_VALUE)))
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
