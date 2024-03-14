package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.type.ReportType;

import java.util.EnumMap;
import java.util.Map;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(Map<String, EnumMap<ReportType, Object>> mapDiff) throws Exception {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(mapDiff);
    }
}
