package hexlet.code.formatter;

import hexlet.code.property.Property;

public final class StylishFormatter implements Formatter {
    private final String template;

    public StylishFormatter() {
        template = "  %s %s: %s\n";
    }

    @Override
    public StringBuilder format(StringBuilder sb, Property prop) {
        switch (prop.getStatus()) {
            case ADDED -> {
                return sb.append(String.format(template, "+", prop.getName(), prop.getNewValue()));
            }
            case DELETED -> {
                return sb.append(String.format(template, "-", prop.getName(), prop.getOldValue()));
            }
            case UNCHANGED -> {
                return sb.append(String.format(template, " ", prop.getName(), prop.getOldValue()));
            }
            case UPDATED -> {
                return sb
                        .append(String.format(template, "-", prop.getName(), prop.getOldValue()))
                        .append(String.format(template, "+", prop.getName(), prop.getNewValue()));
            }
            default -> throw new RuntimeException();
        }
    }
}
