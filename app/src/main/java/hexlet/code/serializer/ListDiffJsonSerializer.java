package hexlet.code.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hexlet.code.list.SortedByNameListDiff;
import hexlet.code.property.Property;
import hexlet.code.status.Status;

import java.io.IOException;

public class ListDiffJsonSerializer extends StdSerializer<SortedByNameListDiff> {
    public ListDiffJsonSerializer() {
        this(null);
    }

    public ListDiffJsonSerializer(Class<SortedByNameListDiff> t) {
        super(t);
    }

    @Override
    public void serialize(SortedByNameListDiff listDiff, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeStartObject();
        for (var prop : listDiff.getAll()) {
            writeProperty(gen, prop, (name, value) -> gen.writeObjectField(name, value.toString()));
        }
        gen.writeEndObject();
    }

    private void writeProperty(JsonGenerator gen, Property prop, Writer writer) throws IOException {
        gen.writeFieldName(prop.getName());

        gen.writeStartObject();
        var status = prop.getStatus();
        writer.write("Status", status);
        if (Status.ADDED.equals(status)) {
            writer.write("file2", prop.getNewValue());
        }
        if (Status.DELETED.equals(status) || Status.UNCHANGED.equals(status)) {
            writer.write("file1", prop.getOldValue());
        }
        if (Status.UPDATED.equals(status)) {
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
