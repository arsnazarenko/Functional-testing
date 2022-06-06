package com.arsnaz.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends Page {

    @FindBy(xpath = "//*[@id=\"start\"]/div[5]/form/table/tbody/tr[4]/td/input")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"start\"]/div[5]/form/table/tbody/tr[2]/td[2]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"start\"]/div[5]/form/table/tbody/tr[1]/td[2]/input")
    private WebElement nameField;


    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void inputName(String login) {
        nameField.sendKeys(login);
    }

    public boolean errorMessageIsExists() {
        return driver.findElements(By.xpath("//*[@id=\"start\"]/div[5]/table/tbody/tr[1]/td/strong/font")).size() > 0;
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

}
