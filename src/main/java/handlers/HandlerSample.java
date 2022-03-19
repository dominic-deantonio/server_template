package handlers;

import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Response;

public class HandlerSample {
    private static final Logger log = LogManager.getLogger(HandlerSample.class);

    public static Handler read = c -> c.result(Response.success("Hello world"));
}
