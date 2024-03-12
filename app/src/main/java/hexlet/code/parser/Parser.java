package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> parseToMap(String content, String dadaFormat) throws IOException {
        if ("yml".equals(dadaFormat)) {
            return new YAMLMapper().readValue(content, new TypeReference<HashMap<String, Object>>() {
            });
        }
        if ("json".equals(dadaFormat)) {

            return new ObjectMapper().readValue(content, new TypeReference<HashMap<String, Object>>() {
            });
        }
        throw new RemoteException();
    }
}
