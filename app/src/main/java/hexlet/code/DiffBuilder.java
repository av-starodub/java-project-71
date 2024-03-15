package hexlet.code;

import hexlet.code.status.Status;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Objects;

import static hexlet.code.status.StatusType.ADDED;
import static hexlet.code.status.StatusType.DELETED;
import static hexlet.code.status.StatusType.UPDATED;
import static hexlet.code.status.StatusType.UNCHANGED;

public final class DiffBuilder {

    private DiffBuilder() {
    }

    public static Map<String, Status> build(Map<String, Object> data1, Map<String, Object> data2) {
        var sortedByNameMapDiff = new LinkedHashMap<String, Status>();
        var keys = new TreeSet<String>(Comparator.naturalOrder());
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            var oldValue = data1.get(key);
            var newValue = data2.get(key);
            if (!data1.containsKey(key)) {
                sortedByNameMapDiff.put(key, new Status(ADDED, oldValue, newValue));
            } else if (!data2.containsKey(key)) {
                sortedByNameMapDiff.put(key, new Status(DELETED, oldValue, newValue));
            } else if (Objects.equals(oldValue, newValue)) {
                sortedByNameMapDiff.put(key, new Status(UNCHANGED, oldValue, newValue));
            } else {
                sortedByNameMapDiff.put(key, new Status(UPDATED, oldValue, newValue));
            }
        }
        return sortedByNameMapDiff;
    }
}
