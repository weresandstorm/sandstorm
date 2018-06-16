package io.sandstorm.spi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ConfigUtils {

    public static <T> Properties loadConfig(Class<T> kClass, String configFileName) {
        try {
            InputStream input = kClass.getClassLoader().getResourceAsStream(configFileName);
            Properties props = new Properties();
            props.load(input);
            return props;
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Failed to load [%s] from classpath", configFileName));
        }
    }

    public static String getRequired(Properties props, String key) {
        String setting = props.getProperty(key);
        if (!StringUtils.isBlank(setting)) {
            return setting;
        }
        throw new IllegalStateException("No configuration setting for key " + key);
    }

    public static String get(Properties props, String key, String defaultValue) {
        String setting = props.getProperty(key);
        return (StringUtils.isBlank(setting) ? defaultValue : setting);
    }
}
