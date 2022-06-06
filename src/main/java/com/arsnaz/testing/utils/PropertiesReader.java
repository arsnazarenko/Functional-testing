package com.arsnaz.testing.utils;

import com.arsnaz.testing.PropertiesConstants;
import com.arsnaz.testing.Context;
import com.arsnaz.testing.exceptions.IllegalPropertyException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private final String PROPERTIES_PATH;

    public PropertiesReader(String propertiesPath) {
        PROPERTIES_PATH = propertiesPath;
    }

    public PropertiesReader() {
        PROPERTIES_PATH = "browsers.properties";
    }

    public void read(Context context) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(inputStream);
        }
        for (PropertiesConstants propKey : PropertiesConstants.values()) {
            String propValue = properties.getProperty(propKey.getPropertyName());
            if (propValue == null || propValue.isEmpty() || propValue.isBlank()) {
                throw new IllegalPropertyException(propKey, propValue);
            }
            propKey.getCallback().accept(context, propValue);
        }
    }
}
