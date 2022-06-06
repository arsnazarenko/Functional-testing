package com.arsnaz.testing.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    protected final WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }


}
