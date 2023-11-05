package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;

public enum PropertiesConfig {
    URL,
    TIMEOUT,
    BROWSER;


    private static final Properties PROPERTIES;
    private static final Logger logger = LogManager.getLogger();

    static {
        PROPERTIES = new Properties();

        String configFile = System.getProperty("user.dir")+"/src/test/resources/config.properties";
        try (FileInputStream inputStream = new FileInputStream(configFile)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    public String get() {
        return PROPERTIES.getProperty(name());
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(get());
    }

    public int getInt() {
        return Integer.parseInt(get());
    }
}
