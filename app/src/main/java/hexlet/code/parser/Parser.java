package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> parseToMap(String path) throws IOException {
        var file = Paths.get(path).toAbsolutePath().normalize().toFile();

        if (file.getName().endsWith("yml")) {
            return new YAMLMapper().readValue(file, new TypeReference<HashMap<String, Object>>() {
            });
        }

        return new ObjectMapper().readValue(file, new TypeReference<HashMap<String, Object>>() {
        });
    }
}
