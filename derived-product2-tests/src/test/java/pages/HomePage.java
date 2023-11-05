package pages;

import config.PropertiesConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.Base;
import utils.ReporterLog;
import utils.ScreenshotUtil;
import utils.WebDriverFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomePage extends Base {

    private final String url = PropertiesConfig.URL.get();
    @FindBy(css = "ul[role=menubar]")
    private List<WebElement> nav;

    @FindBy(css = "button[aria-label='search-input-toggle']")
    private WebElement searchButton;

    @FindBy(css = "button#onetrust-accept-btn-handler")
    private WebElement cookieAcceptBtn;

    @FindBy(css = "footer[data-testid='footer']")
    private WebElement footerEle;

    @FindBy(css = "li[data-testid='footer-list-item'] a")
    private List<WebElement> links;
    @Override
    public boolean isLoaded() {
        try {
            driver.navigate().to(url);
            wait.until(e -> this.cookieAcceptBtn.isDisplayed());
            cookieAcceptBtn.click();
            Thread.sleep(10000);
            return wait.until(e -> searchButton.isDisplayed());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Map<String,String> getFooterLinks() {
        Map<String,String> footerLinks=new HashMap<>();
        try{
            scrollToElement(footerEle);
        for (WebElement link:links){
            String name=link.getText();
            String href=link.getAttribute("href");
            footerLinks.put(name,href);
        }

        }catch(Exception ex){
        ex.printStackTrace();
    }
        return  footerLinks;
    }



}


