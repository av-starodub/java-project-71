package hexlet.code.formatter.formatters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatter.Formatter;
import hexlet.code.property.Property;
import hexlet.code.property.PropertyStatus;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(List<Property> listDiff) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(buildData(listDiff));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Map<String, String>> buildData(List<Property> listDiff) {
        var data = new LinkedHashMap<String, Map<String, String>>();
        listDiff.stream()
                .map(this::toEntry)
                .forEach(entry -> data.put(entry.getKey(), entry.getValue()));
        return data;
    }

    private Map.Entry<String, Map<String, String>> toEntry(Property property) {
        var map = new LinkedHashMap<String, String>();

        var status = property.getStatus();
        map.put("Status", String.valueOf(status));

        if (PropertyStatus.ADDED.equals(status)) {
            map.put("file2", property.getNewValue().toString());
        }
        if (PropertyStatus.DELETED.equals(status) || PropertyStatus.UNCHANGED.equals(status)) {
            map.put("file1", property.getOldValue().toString());
        }
        if (PropertyStatus.UPDATED.equals(status)) {
            map.put("file1", property.getOldValue().toString());
            map.put("file2", property.getNewValue().toString());
        }

        return new AbstractMap.SimpleEntry<>(property.getName(), map);
    }
}
