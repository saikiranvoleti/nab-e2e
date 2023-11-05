package utils;

import config.PropertiesConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WebActions {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    private static final Logger logger = LogManager.getLogger();
    int TIMEOUT = PropertiesConfig.TIMEOUT.getInt();

    public WebActions() {
       this.driver=  WebDriverFactory.getDriver();
        this.wait= new WebDriverWait(this.driver, Duration.ofSeconds(TIMEOUT));
        this.actions = new Actions(driver);

    }
    public  void clickElementWithJavaScript(WebElement element) {
        try {  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
            logger.info("clicked element with JS");
        } catch (Exception e) {
            logger.error("Failed to click element with JS");
            e.printStackTrace();
        }}
    public void waitForElement(WebElement element) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(TIMEOUT))
                    .pollingEvery(Duration.ofSeconds(5))
                    .ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
            wait.until(e -> element.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element");
        } catch (Exception e) {
            logger.error("Failed to scroll to element");
            e.printStackTrace();
        }
    }

    public  void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete"));
    }
}

