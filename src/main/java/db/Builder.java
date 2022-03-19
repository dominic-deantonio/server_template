package db;

import models.SampleClass;
import org.json.JSONObject;

import java.util.Map;

public class Builder {

    public static SampleClass sampleClass(JSONObject j) {
        return sampleClass(j.toMap());
    }

    public static SampleClass sampleClass(Map<String, Object> m) {
        return m == null ? null : new SampleClass();
    }

}
