package com.arsnaz.testing;

import com.arsnaz.testing.pages.DictionaryPage;
import com.arsnaz.testing.pages.MainPage;
import com.arsnaz.testing.pages.WordPage;
import com.arsnaz.testing.utils.PropertiesReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DictionaryPageTest {

    private static Context context;
    private List<WebDriver> drivers;


    @BeforeAll
    static void contextInit() {
        try {
            context = new Context();
            new PropertiesReader().read(context);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        drivers = new ArrayList<>();
        System.setProperty(
                PropertiesConstants.WEBDRIVER_FIREFOX_DRIVER.getPropertyName(),
                context.getGeckoDriver()
        );
        drivers.add(new FirefoxDriver());
        System.setProperty(
                PropertiesConstants.WEBDRIVER_CHROME_DRIVER.getPropertyName(),
                context.getChromeDriver()
        );
        drivers.add(new ChromeDriver());

        drivers.forEach(d -> {
            d.get("https://www.multitran.com/");
            d.manage().window().maximize();
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello", "bye", "duck"})
    public void searchWordTest(String string) {
        drivers.forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickToDictionary();
            DictionaryPage dictionaryPage = new DictionaryPage(driver);
            dictionaryPage.enterWordToSearchField(string);
            dictionaryPage.clickToSearchButton();
            WordPage wordPage = new WordPage(driver);
            Assertions.assertEquals(string, wordPage.getSearchResult());
        });
    }

    @Test
    public void voidInputSearchTest() {
        drivers.forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickToDictionary();
            DictionaryPage dictionaryPage = new DictionaryPage(driver);
            String dictionaryUrl = driver.getCurrentUrl();
            dictionaryPage.enterWordToSearchField("");
            dictionaryPage.clickToSearchButton();
            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.contains(dictionaryUrl));
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"qweqwejkhaskd", "00000000000000", ";::::::::"})
    public void notFoundSearchTest(String word) {
        drivers.forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickToDictionary();
            DictionaryPage dictionaryPage = new DictionaryPage(driver);
            dictionaryPage.enterWordToSearchField(word);
            dictionaryPage.clickToSearchButton();
            WordPage wordPage = new WordPage(driver);
            assertTrue(wordPage.resultsNotFoundMessageExists());
        });
    }


    @Test
    public void swapLanguagesTest() {
        drivers.forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickToDictionary();
            DictionaryPage dictionaryPage = new DictionaryPage(driver);
            String firstLang = dictionaryPage.getFirstLang();
            String secondLang = dictionaryPage.getSecondLang();
            dictionaryPage.clickSwapLangButton();
            Assertions.assertAll(
                    () -> assertEquals(secondLang, dictionaryPage.getFirstLang()),
                    () -> assertEquals(firstLang, dictionaryPage.getSecondLang())
            );
        });
    }

    @AfterEach
    void cleanUp() {
        drivers.forEach(WebDriver::quit);
    }

}
