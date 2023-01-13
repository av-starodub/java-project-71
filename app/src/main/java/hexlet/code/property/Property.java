package hexlet.code.property;

import hexlet.code.status.Status;

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

        public Builder status(Status status) {
            property.status = status;
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
            return property;
        }
    }
}
