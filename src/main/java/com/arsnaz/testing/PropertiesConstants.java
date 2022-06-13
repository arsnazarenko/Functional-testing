package com.arsnaz.testing;

public enum PropertiesConstants {

    /**
     * Drivers
     */

    WEBDRIVER_CHROME_DRIVER("webdriver.chrome.driver"),
    WEBDRIVER_FIREFOX_DRIVER("webdriver.gecko.driver");

    private final String propertyName;
    PropertiesConstants(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

}
