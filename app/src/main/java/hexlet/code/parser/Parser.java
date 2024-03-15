package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> extractToMap(String content, String dataFormat) throws IOException {
        switch (dataFormat) {
            case "yml" -> {
                return new YAMLMapper().readValue(content, new TypeReference<HashMap<String, Object>>() {
                });
            }
            case "json" -> {
                return new ObjectMapper().readValue(content, new TypeReference<HashMap<String, Object>>() {
                });
            }
            default -> throw new IllegalArgumentException("Unsupported data format : %s!".formatted(dataFormat));
        }
    }
}
