package com.arsnaz.testing;

import com.arsnaz.testing.pages.SignInPage;
import com.arsnaz.testing.utils.PropertiesReader;
import com.beust.jcommander.Parameter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginTest {
    private static Context context;
    private List<WebDriver> drivers = new ArrayList<>();


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
        drivers.forEach(d -> {
            d.findElement(By.xpath("/html/body/div[1]/div[3]/div/a[1]")).click();
            SignInPage signInPage = new SignInPage(d);
            signInPage.inputName(name);
            signInPage.inputPassword(password);
            signInPage.clickSignInButton();
            Assertions.assertTrue(signInPage.errorMessageIsExists());
        });
    }

    @AfterEach
    void cleanUp() {
        drivers.forEach(WebDriver::quit);
    }

}
