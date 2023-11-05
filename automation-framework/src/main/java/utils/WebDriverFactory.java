package utils;

import config.PropertiesConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class WebDriverFactory {
    private static final String BROWSER = PropertiesConfig.BROWSER.get();
    private static final ThreadLocal<WebDriver> webDriverThreadLocal = ThreadLocal.withInitial(() -> null);
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE
    }

    private static WebDriver createWebDriver(BrowserType browserType) {
        WebDriver driver = null;

        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        return driver;
    }

    public static WebDriver getDriver() {
     var browserType=   WebDriverFactory.BrowserType.valueOf(BROWSER.toUpperCase());
        WebDriver driver = webDriverThreadLocal.get();
        if (driver == null) {
            driver = createWebDriver(browserType);
            webDriverThreadLocal.set(driver);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = webDriverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            webDriverThreadLocal.remove();
        }
    }
}
