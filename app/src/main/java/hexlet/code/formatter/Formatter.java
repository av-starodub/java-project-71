package hexlet.code.formatter;

import hexlet.code.property.Property;

public interface Formatter {
    StringBuilder format(StringBuilder sb, Property property);
}
