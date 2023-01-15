package hexlet.code.list;

import hexlet.code.property.Property;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class SortedByNameListDiff {
    private final List<Property> listDiff;

    public SortedByNameListDiff(List<Property> listDiff) {
        this.listDiff = listDiff;
    }

    public static SortedByNameListDiff create(Map<String, Object> data1, Map<String, Object> data2) {
        var listDiff = new ArrayList<Property>();
        getAllSortedUniqueKeys(Objects.requireNonNull(data1), Objects.requireNonNull(data2)).forEach(key ->
                listDiff.add(Property.builder()
                        .name(key)
                        .oldValue(getValue(data1, key))
                        .newValue(getValue(data2, key))
                        .build()
                )
        );

        return new SortedByNameListDiff(listDiff);
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
