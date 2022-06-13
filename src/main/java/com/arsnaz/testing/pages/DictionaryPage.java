package com.arsnaz.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DictionaryPage extends Page {


    @FindBy(xpath = "//*[@id=\"start\"]/div[5]/table[1]/tbody/tr/td[1]/b")
    private WebElement dictionaryName;

    @FindBy(xpath = "//*[@id=\"translation\"]/a")
    private WebElement swapLanguagesButton;

    @FindBy(xpath = "//*[@id=\"s\"]")
    private WebElement searchTextField;

    @FindBy(xpath = "//*[@id=\"translation\"]/input[3]")
    private WebElement searchButton;

    public String getDictionaryName() {
        return dictionaryName.getText();
    }

    public DictionaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterWordToSearchField(String string) {
            searchTextField.sendKeys(string);
    }

    public String getFirstLang() {
        String[] words = dictionaryName.getText().split(" ");
        String[] strings = words[0].split("-");
        return strings[0];
    }

    public String getSecondLang() {
        String[] words = dictionaryName.getText().split(" ");
        String[] strings = words[0].split("-");
        return strings[1];
    }

    public void clickSwapLangButton() {
        swapLanguagesButton.click();
        PageFactory.initElements(driver, this);
    }

    public void clickToSearchButton() {
        this.searchButton.click();
    }

    public void clickToTopicFromTable() {
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/table[2]/tbody/tr[2]/td[1]/a")).click();
    }

}
