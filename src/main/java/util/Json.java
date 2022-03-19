package util;

import org.json.JSONObject;

public class Json {

    public static Long getLong(JSONObject j, String id) {
        return getLongOrDefault(j, id, null);
    }

    public static Long getLongOrDefault(JSONObject j, String id, Long def) {
        if (j.has(id))
            return j.getLong(id);

        return def;
    }

    public static String getString(JSONObject j, String id) {
        return getStringOrDefault(j, id, null);
    }

    public static String getStringOrDefault(JSONObject j, String id, String def) {
        if (j.has(id))
            return j.getString(id);

        return def;
    }

    public static Integer getInt(JSONObject j, String id) {
        return getIntOrDefault(j, id, null);
    }

    public static Integer getIntOrDefault(JSONObject j, String id, Integer def) {
        if (j.has(id))
            return j.getInt(id);

        return def;
    }

    public static Double getDouble(JSONObject j, String id) {
        return getDoubleOrDefault(j, id, null);
    }

    public static Double getDoubleOrDefault(JSONObject j, String id, Double def) {
        if (j.has(id))
            return j.getDouble(id);

        return def;
    }
}
