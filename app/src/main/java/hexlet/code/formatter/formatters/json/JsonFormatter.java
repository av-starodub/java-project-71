package hexlet.code.formatter.formatters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatter.Formatter;
import hexlet.code.property.Property;

import java.util.List;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(List<Property> listDiff) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listDiff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
