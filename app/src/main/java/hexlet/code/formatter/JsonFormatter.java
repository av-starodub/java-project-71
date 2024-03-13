package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.property.Property;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(List<Property> listDiff) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        var data = build(listDiff);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }

    private Map<String, Map<String, String>> build(List<Property> listDiff) {
        var data = new LinkedHashMap<String, Map<String, String>>();
        listDiff.stream()
                .map(this::mapToEntry)
                .forEach(entry -> data.put(entry.getKey(), entry.getValue()));
        return data;
    }

    private Map.Entry<String, Map<String, String>> mapToEntry(Property property) {
        var data = new LinkedHashMap<String, String>();
        var status = property.getStatus();
        data.put("Status", String.valueOf(status));
        switch (status) {
            case ADDED -> data.put("file2", property.getNewValue().toString());
            case DELETED, UNCHANGED -> data.put("file1", property.getOldValue().toString());
            case UPDATED -> {
                data.put("file1", property.getOldValue().toString());
                data.put("file2", property.getNewValue().toString());
            }
            default -> throw new IllegalStateException("Invalid property status: '%s'!".formatted(status));
        }
        return new AbstractMap.SimpleEntry<>(property.getName(), data);
    }
}
