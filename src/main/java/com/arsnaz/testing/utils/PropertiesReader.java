package com.arsnaz.testing.utils;

import com.arsnaz.testing.Context;
import com.arsnaz.testing.PropertiesConstants;
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

    private boolean propertyIsValid(String propValue) {
        return propValue != null && !propValue.isEmpty() && !propValue.isBlank();
    }

    public PropertiesReader() {
        PROPERTIES_PATH = "browsers.properties";
    }

    public void read(Context context) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(inputStream);
        }

        String chromeDriver = properties.getProperty(PropertiesConstants.WEBDRIVER_CHROME_DRIVER.getPropertyName());
        if (propertyIsValid(chromeDriver)) {
            context.setChromeDriver(chromeDriver);
        } else {
            throw new IllegalPropertyException(PropertiesConstants.WEBDRIVER_CHROME_DRIVER, chromeDriver);
        }

        String geckoDriver = properties.getProperty(PropertiesConstants.WEBDRIVER_FIREFOX_DRIVER.getPropertyName());
        if (propertyIsValid(geckoDriver)) {
            context.setGeckoDriver(geckoDriver);
        } else {
            throw new IllegalPropertyException(PropertiesConstants.WEBDRIVER_FIREFOX_DRIVER, geckoDriver);
        }
    }
}
