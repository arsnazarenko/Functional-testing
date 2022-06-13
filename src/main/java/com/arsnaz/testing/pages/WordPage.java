package com.arsnaz.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WordPage extends Page {

    public WordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getSearchResult() {
        return driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/table[1]/tbody/tr[2]/td/a")).getText();
    }

    public boolean resultsNotFoundMessageExists() {
        return driver.findElements(By.xpath("//*[@id=\"start\"]/div[5]/a")).size() > 0;
    }

}
