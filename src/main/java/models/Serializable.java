package models;

import org.json.JSONObject;

import java.util.Map;

abstract class Serializable {

    public JSONObject toJson(){
        return new JSONObject(toMap());
    }

    public abstract Map<String, Object> toMap();

}
