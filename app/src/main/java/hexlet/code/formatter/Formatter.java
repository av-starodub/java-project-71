package hexlet.code.formatter;

import hexlet.code.type.ReportType;

import java.util.EnumMap;
import java.util.Map;

public interface Formatter {

    String format(Map<String, EnumMap<ReportType, Object>> listDiff) throws Exception;

}
