package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.status.Status;

import java.util.Map;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(Map<String, Status> mapDiff) throws Exception {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(mapDiff);
    }
}
