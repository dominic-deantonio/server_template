package util;

import io.javalin.http.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Response {
    private static final Logger log = LogManager.getLogger(Response.class);

    public static String authError(Context c){
        return clientError(c, "authentication error");
    }

    public static String clientError(Context c, String message) {
        return clientError(c, message, null);
    }

    public static String clientError(Context c, String message, Exception e) {
        c.status(400);
        JSONObject j = getErrorJson(message);

        String logMsg = "Client error: " + message;

        if (e == null) {
            log.debug(logMsg);
        } else {
            log.debug(logMsg, e);
        }

        return j.toString();
    }

    public static String serverError(Context c) {
        return serverError(c, "Server error", null);
    }

    public static String serverError(Context c, String message) {
        return serverError(c, message, null);
    }

    public static String serverError(Context c, Exception e) {
        return serverError(c, "Server error", e);
    }

    public static String serverError(Context c, String message, Exception e) {
        c.status(500);

        String logMsg = "Server error: " + message;

        if (e == null) {
            log.debug(logMsg);
        } else {
            log.debug(logMsg, e);
        }

        JSONObject j = getErrorJson(message);
        return j.toString();
    }

    public static String success() {
        return success("ok");
    }

    public static String success(Object payload) {
        JSONObject j = new JSONObject();
        j.put("data", payload);
        return j.toString();
    }

    private static JSONObject getErrorJson(String data) {
        JSONObject j = new JSONObject();
        j.put("error", data);
        return j;
    }
}
