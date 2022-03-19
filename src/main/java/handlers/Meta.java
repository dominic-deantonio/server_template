package handlers;

import io.javalin.http.Handler;
import util.Response;

public class Meta {

    public static Handler health = c -> c.result(Response.success("ok"));

}
