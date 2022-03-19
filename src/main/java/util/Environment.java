package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class Environment {
    private static Properties properties;
    private static final String fileName = ".properties";
    private static final Logger log = LogManager.getLogger(Environment.class);

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
            log.info("Initialized properties");
        } catch (Exception e) {
            log.error("Exception occurred while initializing properties", e);
        }

    }

    public static String getOrDefault(String key, String defaultVal) {
        String val = get(key);
        return val == null ? defaultVal : val;
    }

    public static Boolean getOrDefault(String key, Boolean defaultVal) {
        try {
            return Boolean.parseBoolean(get(key));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Integer getOrDefault(String key, Integer defaultVal) {
        try {
            return Integer.parseInt(get(key));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static String get(String property) {
        return properties.getProperty(property);
    }
}