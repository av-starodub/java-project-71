package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> parseToMap(File file) throws IOException {
        if (file.getName().endsWith("yml")) {
            return new YAMLMapper().readValue(file, new TypeReference<HashMap<String, Object>>() {
            });
        }
        return new ObjectMapper().readValue(file, new TypeReference<HashMap<String, Object>>() {
        });
    }

}
