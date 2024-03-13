package hexlet.code.property;

import static hexlet.code.property.PropertyStatus.ADDED;
import static hexlet.code.property.PropertyStatus.UPDATED;
import static hexlet.code.property.PropertyStatus.UNCHANGED;
import static hexlet.code.property.PropertyStatus.DELETED;
import static java.util.Objects.isNull;

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
            if (isNull(property.oldValue)) {
                property.status = ADDED;
            } else if (isNull(property.newValue)) {
                property.status = DELETED;
            } else if (property.oldValue.equals(property.newValue)) {
                property.status = UNCHANGED;
            } else {
                property.status = UPDATED;
            }
        }
    }
}
