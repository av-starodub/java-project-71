package hexlet.code.formatter;

import hexlet.code.status.Status;

import java.util.Map;

public interface Formatter {

    String format(Map<String, Status> mapDiff) throws Exception;

}
