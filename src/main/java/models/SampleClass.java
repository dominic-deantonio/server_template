package models;

import java.util.HashMap;
import java.util.Map;

public class SampleClass extends Serializable {
    
    private String id;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        m.put("id", id);
        return m;
    }
}
