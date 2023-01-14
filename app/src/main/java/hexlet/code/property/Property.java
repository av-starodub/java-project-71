package hexlet.code.property;

import hexlet.code.status.Status;

import java.util.Objects;

public final class Property {
    private String name;
    private Status status;
    private Object oldValue;
    private Object newValue;

    public String getName() {
        return name;
    }


    public Status getStatus() {
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

        public Builder name(String name) {
            property.name = name;
            return this;
        }

        public Builder oldValue(Object value) {
            property.oldValue = value;
            return this;
        }

        public Builder newValue(Object value) {
            property.newValue = value;
            return this;
        }

        public Property build() {
            setPropertyStatus();
            return property;
        }

        private void setPropertyStatus() {
            if (Objects.isNull(property.oldValue)) {
                property.status = Status.ADDED;
            } else if (Objects.isNull(property.newValue)) {
                property.status = Status.DELETED;
            } else if (property.oldValue.equals(property.newValue)) {
                property.status = Status.UNCHANGED;
            } else {
                property.status = Status.UPDATED;
            }
        }
    }
}
