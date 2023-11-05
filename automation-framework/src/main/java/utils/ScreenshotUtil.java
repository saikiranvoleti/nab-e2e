package utils;

import io.cucumber.java.Scenario;
import listener.CucumberBaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenshotUtil {

    private final static AtomicInteger counter = new AtomicInteger();
    private static final Logger logger = LogManager.getLogger();
    public static void takeScreenshot(){
        String screenshotName = null;
        try {
            TakesScreenshot ts = (TakesScreenshot) WebDriverFactory.getDriver();
            File file = ts.getScreenshotAs(OutputType.FILE);
            screenshotName = "screenshot" + "_" + counter.incrementAndGet() + ".png";
            String screenshotPath = CucumberBaseTest.reportFolderPath +"/screenshots/"+screenshotName;
            File screenshot=new File(screenshotPath);
            FileUtils.copyFile(file, screenshot);
            ReporterLog.logImage(screenshotName,screenshotPath);
            logger.info("attached screenshot "+screenshotName+" to report "+screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("unable to capture screenshot" + e);
        }
    }

}
