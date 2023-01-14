package hexlet.code.differ;

import hexlet.code.builder.PresentationBuilder;
import hexlet.code.formatter.factory.FormatterFactory;
import hexlet.code.parser.Parser;
import hexlet.code.property.Property;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {
    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String presentationFormat) throws IOException {
        return PresentationBuilder.build(
                createQueue(Parser.parseToMap(getFile(filePath1)), Parser.parseToMap(getFile(filePath2))),
                FormatterFactory.create(presentationFormat)
        );
    }

    private static ArrayDeque<Property> createQueue(Map<String, Object> data1, Map<String, Object> data2) {
        var queue = new ArrayDeque<Property>();
        getUniqueKeys(data1, data2).forEach(key ->
                queue.addLast(Property.builder()
                        .name(key)
                        .oldValue(getValue(data1, key))
                        .newValue(getValue(data2, key))
                        .build()
                )
        );
        return queue;
    }

    private static Object getValue(Map<String, Object> data, String key) {
        var value = data.get(key);
        return data.containsKey(key) && Objects.isNull(value) ? "null" : value;
    }

    private static File getFile(String path) {
        return new File(new File(path).getAbsolutePath());
    }

    private static Set<String> getUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {
        var uniqueKeys = new TreeSet<String>(Comparator.naturalOrder());
        uniqueKeys.addAll(map1.keySet());
        uniqueKeys.addAll(map2.keySet());
        return uniqueKeys;
    }
}
