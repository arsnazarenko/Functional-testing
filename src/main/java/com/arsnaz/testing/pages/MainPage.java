package com.arsnaz.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    @FindBy(xpath = "//*[@id=\"start\"]/div[3]/div/a[1]")
    WebElement signInButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void signOut() {
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[3]/div/a[2]")).click();
    }

    public void signIn() {
        signInButton.click();
    }

    public boolean isAuthorized(String login) {
        return driver
                .findElement(By.xpath("//*[@id=\"start\"]/div[3]/div/a[1]"))
                .getText()
                .equals(login);
    }

    public void clickToDictionary() {
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/table/tbody/tr[3]/td[1]/a[1]")).click();
    }
}
