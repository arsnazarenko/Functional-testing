package com.arsnaz.testing;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private String geckoDriver;
    private String chromeDriver;

    public String getGeckoDriver() {
        return geckoDriver;
    }

    public void setGeckoDriver(String geckoDriver) {
        this.geckoDriver = geckoDriver;
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver(String chromeDriver) {
        this.chromeDriver = chromeDriver;
    }
}
