package hexlet.code;

import hexlet.code.type.ReportType;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Objects;

import static hexlet.code.type.ReportType.NEW_VALUE;
import static hexlet.code.type.ReportType.OLD_VALUE;
import static hexlet.code.type.ReportType.STATUS;

import static hexlet.code.type.StatusType.ADDED;
import static hexlet.code.type.StatusType.DELETED;
import static hexlet.code.type.StatusType.UPDATED;
import static hexlet.code.type.StatusType.UNCHANGED;

public final class DiffBuilder {

    private DiffBuilder() {
    }

    public static Map<String, EnumMap<ReportType, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
        var properties = new LinkedHashMap<String, EnumMap<ReportType, Object>>();
        var keys = new TreeSet<String>(Comparator.naturalOrder());
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            var oldValue = data1.get(key);
            var newValue = data2.get(key);
            var report = new EnumMap<>(ReportType.class);
            if (!data1.containsKey(key)) {
                report.put(STATUS, ADDED);
                report.put(NEW_VALUE, newValue);
            } else if (!data2.containsKey(key)) {
                report.put(STATUS, DELETED);
                report.put(OLD_VALUE, oldValue);
            } else if (Objects.equals(oldValue, newValue)) {
                report.put(STATUS, UNCHANGED);
                report.put(OLD_VALUE, oldValue);
            } else {
                report.put(STATUS, UPDATED);
                report.put(OLD_VALUE, oldValue);
                report.put(NEW_VALUE, newValue);
            }
            properties.put(key, report);
        }
        return properties;
    }
}
