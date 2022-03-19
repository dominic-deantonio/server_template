package constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Environment;

public class Constants {

    private static final Logger log = LogManager.getLogger(Constants.class);

    public static final String NAME = "App Name";
    public static final String VERSION = "0.0.1";
    public static final Integer PORT = Environment.getOrDefault("port", 8000);
    public static final Boolean IS_DEV_MODE = Environment.getOrDefault("devmode", false);
    public static final String DB_URL = Environment.get("db.url");
    public static final String DB_USER = Environment.get("db.user");
    public static final String DB_PASSWORD = Environment.get("db.password");

    public static void initialize() {
        log.debug("Name: " + NAME);
        log.debug("Version: " + VERSION);
        log.debug("Port: " + PORT);
        log.debug("Is dev mode: " + IS_DEV_MODE);
        log.debug("DB URL: " + DB_URL);
        log.debug("DB User: " + DB_USER);
        log.debug("Initialized constants");
    }

    public static final String ART = """
            ART
            ART
            ART
            ART
            ART
            ART
            ART
            ART
            ART
            ART
            """;
}
