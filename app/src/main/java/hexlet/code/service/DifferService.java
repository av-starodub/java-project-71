package hexlet.code.service;

import hexlet.code.property.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class DifferService {
    private DifferService() {
    }

    public static List<Property> getAllSortedByName(Map<String, Object> data1, Map<String, Object> data2) {
        var properties = new ArrayList<Property>();
        getAllSortedUniqueKeys(Objects.requireNonNull(data1), Objects.requireNonNull(data2)).forEach(key ->
                properties.add(Property.builder()
                        .name(key)
                        .oldValue(getValue(data1, key))
                        .newValue(getValue(data2, key))
                        .build()
                )
        );
        return properties;
    }

    private static Set<String> getAllSortedUniqueKeys(Map<String, Object> data1, Map<String, Object> data2) {
        var uniqueKeys = new TreeSet<String>(Comparator.naturalOrder());
        uniqueKeys.addAll(data1.keySet());
        uniqueKeys.addAll(data2.keySet());
        return uniqueKeys;
    }

    private static Object getValue(Map<String, Object> data, String key) {
        var value = data.get(key);
        return data.containsKey(key) && Objects.isNull(value) ? "null" : value;
    }
    public static File getFile(String path) {
        return new File(new File(path).getAbsolutePath());
    }
}
