package hexlet.code.formatter;

import hexlet.code.status.Status;

import java.util.Map;

public final class StylishFormatter implements Formatter {
    private static final String TEMPLATE = "  %s %s: %s\n";

    @Override
    public String format(Map<String, Status> mapDiff) {
        var sb = new StringBuilder();
        sb.append("{\n");
        for (var entry : mapDiff.entrySet()) {
            var key = entry.getKey();
            var report = entry.getValue();
            var status = report.getStatus();
            switch (status) {
                case ADDED -> sb.append(TEMPLATE.formatted("+", key, report.getNewValue()));
                case DELETED -> sb.append(TEMPLATE.formatted("-", key, report.getOldValue()));
                case UNCHANGED -> sb.append(TEMPLATE.formatted(" ", key, report.getOldValue()));
                case UPDATED -> {
                    sb.append(TEMPLATE.formatted("-", key, report.getOldValue()));
                    sb.append(TEMPLATE.formatted("+", key, report.getNewValue()));
                }
                default -> throw new IllegalStateException("Invalid property status: '%s'!".formatted(status));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
