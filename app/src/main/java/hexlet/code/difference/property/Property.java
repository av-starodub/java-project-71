package hexlet.code.difference.property;

import java.util.Objects;

public final class Property {
    private String name;
    private PropertyStatus status;
    private Object oldValue;
    private Object newValue;

    private Property() {
    }

    public String getName() {
        return name;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Property property = new Property();

        public final Builder name(String name) {
            property.name = name;
            return this;
        }

        public final Builder oldValue(Object value) {
            property.oldValue = value;
            return this;
        }

        public final Builder newValue(Object value) {
            property.newValue = value;
            return this;
        }

        public final Property build() {
            setPropertyStatus();
            return property;
        }

        private void setPropertyStatus() {
            if (Objects.isNull(property.oldValue)) {
                property.status = PropertyStatus.ADDED;
            } else if (Objects.isNull(property.newValue)) {
                property.status = PropertyStatus.DELETED;
            } else if (property.oldValue.equals(property.newValue)) {
                property.status = PropertyStatus.UNCHANGED;
            } else {
                property.status = PropertyStatus.UPDATED;
            }
        }
    }
}
