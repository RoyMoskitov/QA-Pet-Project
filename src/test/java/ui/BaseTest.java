package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    @BeforeAll
    public static void setUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "122.0");
        Configuration.remote = System.getProperty("remote.url", "http://localhost:4444/wd/hub");
//        Configuration.browserBinary = "C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;

//        Configuration.browserCapabilities.setCapability("enableVNC", true);
//        Configuration.browserCapabilities.setCapability("enableVideo", true);

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }
}
