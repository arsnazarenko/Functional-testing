package com.arsnaz.testing;
import java.util.function.BiConsumer;
public enum PropertiesConstants {

    /**
     * Drivers
     */

    WEBDRIVER_CHROME_DRIVER("webdriver.chrome.driver", Context::setChromeDriver),
    WEBDRIVER_FIREFOX_DRIVER("webdriver.gecko.driver", Context::setGeckoDriver);

    private final String propertyName;
    private final BiConsumer<Context, String> callback;

    PropertiesConstants(String propertyName, BiConsumer<Context, String> callback) {
        this.callback = callback;
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public BiConsumer<Context, String> getCallback() {
        return callback;
    }
}
