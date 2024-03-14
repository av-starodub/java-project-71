package hexlet.code.formatter;

import hexlet.code.type.StatusType;
import hexlet.code.type.ReportType;

import java.util.EnumMap;
import java.util.Map;

import static hexlet.code.type.ReportType.STATUS;
import static hexlet.code.type.ReportType.OLD_VALUE;
import static hexlet.code.type.ReportType.NEW_VALUE;


public final class StylishFormatter implements Formatter {
    private static final String TEMPLATE = "  %s %s: %s\n";

    @Override
    public String format(Map<String, EnumMap<ReportType, Object>> listDiff) {
        var sb = new StringBuilder();
        sb.append("{\n");
        for (var entry : listDiff.entrySet()) {
            var key = entry.getKey();
            var report = entry.getValue();
            var status = (StatusType) report.get(STATUS);
            switch (status) {
                case ADDED -> sb.append(TEMPLATE.formatted("+", key, report.get(NEW_VALUE)));
                case DELETED -> sb.append(TEMPLATE.formatted("-", key, report.get(OLD_VALUE)));
                case UNCHANGED -> sb.append(TEMPLATE.formatted(" ", key, report.get(OLD_VALUE)));
                case UPDATED -> {
                    sb.append(TEMPLATE.formatted("-", key, report.get(OLD_VALUE)));
                    sb.append(TEMPLATE.formatted("+", key, report.get(NEW_VALUE)));
                }
                default -> throw new IllegalStateException("Invalid property status: '%s'!".formatted(status));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
