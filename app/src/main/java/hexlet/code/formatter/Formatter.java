package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.property.Property;

import java.util.List;

public interface Formatter {
    String format(List<Property> listDiff) throws JsonProcessingException;
}
