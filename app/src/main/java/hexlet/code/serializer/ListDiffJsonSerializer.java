package hexlet.code.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hexlet.code.property.Property;
import hexlet.code.property.PropertyStatus;

import java.io.IOException;
import java.util.List;

public final class ListDiffJsonSerializer extends StdSerializer<List<Property>> {
    public ListDiffJsonSerializer() {
        this(null);
    }

    public ListDiffJsonSerializer(Class<List<Property>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Property> listDiff, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeStartObject();
        for (var prop : listDiff) {
            writeProperty(gen, prop, (name, value) -> gen.writeObjectField(name, value.toString()));
        }
        gen.writeEndObject();
    }

    private void writeProperty(JsonGenerator gen, Property prop, Writer writer) throws IOException {
        gen.writeFieldName(prop.getName());

        gen.writeStartObject();
        var status = prop.getStatus();
        writer.write("Status", status);
        if (PropertyStatus.ADDED.equals(status)) {
            writer.write("file2", prop.getNewValue());
        }
        if (PropertyStatus.DELETED.equals(status) || PropertyStatus.UNCHANGED.equals(status)) {
            writer.write("file1", prop.getOldValue());
        }
        if (PropertyStatus.UPDATED.equals(status)) {
            writer.write("file1", prop.getOldValue());
            writer.write("file2", prop.getNewValue());
        }
        gen.writeEndObject();
    }

    @FunctionalInterface
    private interface Writer {
        void write(String field, Object value) throws IOException;
    }
}
