package hexlet.code.formatter.formatters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hexlet.code.formatter.Formatter;
import hexlet.code.list.SortedByNameListDiff;
import hexlet.code.serializer.ListDiffJsonSerializer;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(SortedByNameListDiff listDiff) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(SortedByNameListDiff.class, new ListDiffJsonSerializer());
        mapper.registerModule(module);
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listDiff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
