package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseToMap(File file) throws IOException {
        return new ObjectMapper().readValue(file, new TypeReference<HashMap<String, Object>>() {
        });
    }
}
