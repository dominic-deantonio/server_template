package handlers;

import com.google.firebase.auth.UserRecord;
import io.javalin.core.security.AccessManager;
import io.javalin.core.security.RouteRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.Firebase;
import util.Response;

public class Auth {

    private static final Logger log = LogManager.getLogger(Auth.class);

    public enum Role implements RouteRole {
        PUBLIC, LOGGED_IN
    }

    public static AccessManager accessControlHandler = (handler, c, set) -> {

        if (set.contains(Role.PUBLIC)) {
            handler.handle(c);
        } else {
            try {
                String token = c.header("x-token");

                if (token == null || token.isEmpty())
                    throw new Exception("Token was not found in header");

                UserRecord user = Firebase.verifyToken(token);
                c.attribute("user", user);
                log.printf(Level.DEBUG, "User %s accessing %s", user.getUid(), c.endpointHandlerPath());
                handler.handle(c);
            } catch (Exception e) {
                log.printf(Level.WARN, "Someone at IP %s tried to access %s but could not get user from request", c.ip(), c.endpointHandlerPath());
                log.debug("Error was: " + e.getMessage());
                c.result(Response.clientError(c, "unauthorized"));
            }
        }
    };
}
