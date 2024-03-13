package hexlet.code.formatter;

import hexlet.code.property.Property;

import java.util.List;

public interface Formatter {
    String format(List<Property> listDiff) throws Exception;
}
