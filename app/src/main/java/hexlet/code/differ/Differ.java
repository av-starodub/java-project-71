package hexlet.code.differ;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        return genDiff(comvertJsonToMap(getFile(filePath1)), comvertJsonToMap(getFile(filePath2)));
    }

    public static Map<String, Object> comvertJsonToMap(File json) throws IOException {
        var mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        return mapper.readValue(json, typeRef);
    }

    private static String genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        var diff = new StringBuilder();
        diff.append("{\n");
        getUniqueKeys(data1, data2).forEach(key -> addDiff(diff, key, data1.get(key), data2.get(key)));
        diff.append("}");
        return diff.toString();
    }

    private static void addDiff(StringBuilder diff, String key, Object value1, Object value2) {
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
        return new File(path);
    }

    public static Set<String> getUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {
        var uniqueKeys = new TreeSet<String>(Comparator.naturalOrder());
        uniqueKeys.addAll(map1.keySet());
        uniqueKeys.addAll(map2.keySet());
        return uniqueKeys;
    }
}
