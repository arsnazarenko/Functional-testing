package com.arsnaz.testing;

import com.arsnaz.testing.pages.MainPage;
import com.arsnaz.testing.pages.SignInPage;
import com.arsnaz.testing.utils.PropertiesReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignInPageTest {
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
                PropertiesConstants.WEBDRIVER_CHROME_DRIVER.getPropertyName(),
                context.getChromeDriver()
        );
        drivers.add(new ChromeDriver());
        System.setProperty(
                PropertiesConstants.WEBDRIVER_FIREFOX_DRIVER.getPropertyName(),
                context.getGeckoDriver()
        );
        drivers.add(new FirefoxDriver());
        drivers.forEach(d -> {
            d.manage().window().maximize();
            d.get("https://www.multitran.com/");
        });
    }





    @ParameterizedTest
    @CsvSource({
            "senya, 123456789",
            "itmosuperpuper, 0123456789",
            "multitranslatehack, password"
    })
    void invalidSignInTest(String name, String password) {
        drivers.forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.signIn();
            SignInPage signInPage = new SignInPage(driver);
            signInPage.enterName(name);
            signInPage.enterPassword(password);
            signInPage.clickSignInButton();
            assertTrue(signInPage.errorMessageIsExists());

        });
    }


    @ParameterizedTest
    @CsvSource({
            "arsnazarenko, test2022"
    })
    void successfulSignInTest(String name, String password) {
        drivers.forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.signIn();
            SignInPage signInPage = new SignInPage(driver);
            signInPage.enterName(name);
            signInPage.enterPassword(password);
            signInPage.clickSignInButton();
            assertTrue(mainPage.isAuthorized(name));
            mainPage.signOut();
        });
    }

    @ParameterizedTest
    @CsvSource({
            "arsnazarenko, test2022"
    })
    void logOutTest(String name, String password) {
        drivers.forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.signIn();
            SignInPage signInPage = new SignInPage(driver);
            signInPage.enterName(name);
            signInPage.enterPassword(password);
            signInPage.clickSignInButton();
            mainPage.signOut();
            assertFalse(mainPage.isAuthorized(name));
        });
    }

    @AfterEach
    void cleanUp() {
        drivers.forEach(WebDriver::quit);
    }

}

