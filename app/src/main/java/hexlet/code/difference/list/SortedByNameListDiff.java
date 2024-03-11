package hexlet.code.difference.list;

import hexlet.code.difference.property.Property;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class SortedByNameListDiff {
    private final List<Property> listDiff;

    private SortedByNameListDiff(List<Property> sortedProperties) {
        this.listDiff = sortedProperties;
    }

    public static SortedByNameListDiff create(Map<String, Object> data1, Map<String, Object> data2) {
        var sortedProperties = new ArrayList<Property>();
        getAllSortedUniqueKeys(Objects.requireNonNull(data1), Objects.requireNonNull(data2)).forEach(key ->
                sortedProperties.add(Property.builder()
                        .name(key)
                        .oldValue(getValue(data1, key))
                        .newValue(getValue(data2, key))
                        .build()
                )
        );
        return new SortedByNameListDiff(sortedProperties);
    }

    public List<Property> getAll() {
        return List.copyOf(listDiff);
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
}
