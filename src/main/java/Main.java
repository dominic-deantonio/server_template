import constants.Constants;
import constants.Paths;
import handlers.Auth;
import handlers.HandlerSample;
import handlers.Meta;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.Firebase;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        printStartupArt();

        if (!initialize())
            System.exit(1);

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enforceSsl = false;
            config.accessManager(Auth.accessControlHandler);
        });

        app.start(Constants.PORT);

        // Before processing
        app.before(c -> c.header("content-type", "application/json"));

        // Endpoints
        app.get(Paths.HEALTH, Meta.health, Auth.Role.PUBLIC);
        app.get(Paths.SAMPLE, HandlerSample.read, Auth.Role.LOGGED_IN);

        // After processing
        app.after(c -> {});
    }

    private static boolean initialize() {
        try {
            System.out.println("Initialization ----------------------------");
            Constants.initialize();
            Firebase.initialize();
//            Db.initialize();
            System.out.println("---------------------------- End Initialization");
            return true;
        } catch (Exception e) {
            log.fatal("Initialization error", e);
            log.debug("Aborting startup");
            return false;
        }
    }

    private static void printStartupArt() {
        System.out.println(Constants.ART);
    }
}
