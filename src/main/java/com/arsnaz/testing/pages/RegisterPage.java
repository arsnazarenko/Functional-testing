package com.arsnaz.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends Page {

    @FindBy(xpath = "//*[@id=\"UserName\"]")
    private WebElement userNameField;

    @FindBy(xpath = "/html/body/div[1]/div[4]/div/form/table/tbody/tr[2]/td[2]/input")
    private WebElement emailField;

    @FindBy(xpath = "/html/body/div[1]/div[4]/div/form/table/tbody/tr[3]/td[2]/div[1]/input")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/div[1]/div[4]/div/form/table/tbody/tr[4]/td[2]/input")
    private WebElement repeatPasswordField;

    @FindBy(xpath = "/html/body/div[1]/div[4]/div/form/table/tbody/tr[5]/td[2]/textarea")
    private WebElement additionalInformationField;
    @FindBy(xpath = "/html/body/div[1]/div[4]/div/form/table/tbody/tr[6]/td[1]/input")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"recaptcha-anchor\"]/div[1]")
    private WebElement captchaCheckBox;



    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void inputUserName(String login) {

    }
    public void inputEmail(String login) {

    }
    public void inputPassword(String login) {

    }

    public void inputAdditionalInformation(String login) {

    }

    public void clickSaveInButton() {
        saveButton.click();
    }


    public void inputRepeatPassword(String login) {

    }

    public boolean errorMessageIsExists() {
        return driver.findElements(By.xpath("//*[@id=\"start\"]/div[5]/table/tbody/tr[1]/td/strong/font")).size() > 0;
    }
}
