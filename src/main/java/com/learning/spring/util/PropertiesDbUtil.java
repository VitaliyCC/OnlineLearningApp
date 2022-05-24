package com.learning.spring.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesDbUtil {
    private static final Properties PROPERTIES = new Properties();


    static {
        loadProperties();
    }

    private PropertiesDbUtil() {
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesDbUtil.class.getClassLoader().getResourceAsStream("bd.properties");) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
