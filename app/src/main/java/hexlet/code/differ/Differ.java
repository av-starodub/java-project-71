package hexlet.code.differ;

import hexlet.code.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {
    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return genDiff(Parser.parseToMap(getFile(filePath1)), Parser.parseToMap(getFile(filePath2)));
    }

    private static String genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        var diff = new StringBuilder();
        diff.append("{\n");
        getUniqueKeys(data1, data2).forEach(key -> addString(diff, key, getValue(data1, key), getValue(data2, key)));
        diff.append("}");
        return diff.toString();
    }

    private static Object getValue(Map<String, Object> data, String key) {
        var value = data.get(key);
        return data.containsKey(key) && Objects.isNull(value) ? "null" : value;
    }

    private static void addString(StringBuilder diff, String key, Object value1, Object value2) {
        if (Objects.isNull(value1)) {
            add(diff, "+", key, value2);
            return;
        }
        if (Objects.isNull(value2)) {
            add(diff, "-", key, value1);
            return;
        }
        if (value1.equals(value2)) {
            add(diff, " ", key, value1);
            return;
        }
        add(diff, "-", key, value1);
        add(diff, "+", key, value2);
    }

    private static void add(StringBuilder diff, String prefix, String key, Object value) {
        diff.append("  ").append(prefix).append(" ").append(key).append(": ").append(value).append("\n");
    }

    private static File getFile(String path) {
        return new File(new File(path).getAbsolutePath());
    }

    public static Set<String> getUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {
        var uniqueKeys = new TreeSet<String>(Comparator.naturalOrder());
        uniqueKeys.addAll(map1.keySet());
        uniqueKeys.addAll(map2.keySet());
        return uniqueKeys;
    }
}