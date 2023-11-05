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

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends Base {

    private  final String url =  PropertiesConfig.URL.get();
    @FindBy(css = "ul[role=menubar]")
    private List<WebElement> nav;

    @FindBy(css = "button[aria-label='search-input-toggle']")
    private WebElement searchButton;

    @FindBy(css = "li.menu-item")
    private List<WebElement> primary_menu;

    @FindBy(css = "div[role='tablist']>button")
    private List<WebElement> slideButtonList;
    private By submenu = By.cssSelector("ul[role='menu'] li[role='menuitem']");
    private By  slideNames=By.xpath("//span[contains(@class,'tileHeroStoriesButtonTitle')]");
    @FindBy(css = "button#onetrust-accept-btn-handler")
    private WebElement cookieAcceptBtn;
    @Override
    public boolean isLoaded() {
        try {
            driver.navigate().to(url);
            wait.until(e->this.cookieAcceptBtn.isDisplayed());
            cookieAcceptBtn.click();
            Thread.sleep(5000);
            return wait.until(e->searchButton.isDisplayed());
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<String> getSubMenuItems(String menu){

       WebElement menuItem=primary_menu.stream().
                filter(e->e.getText().trim().toLowerCase().contains(menu.toLowerCase()))
                .findAny().get();
       actions.moveToElement(menuItem).perform();
       ReporterLog.lotText("");
       return menuItem.findElements(submenu).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public long getSlideDuration(String slideName) {
        long duration = 0;
        for (WebElement slide : slideButtonList) {
            if (slide.getText().trim().equalsIgnoreCase(slideName)) {
                wait.until(ExpectedConditions.attributeToBe(slide, "aria-selected", "true"));
                long startTime = System.currentTimeMillis();
                wait.until(ExpectedConditions.attributeToBe(slide, "aria-selected", "false"));
                long endTime = System.currentTimeMillis();
                duration = endTime - startTime;
                ReporterLog.lotText("Slide " + slideName + " duration: " + duration + " milliseconds");
                break;
            }
        }
            return duration / 1000;

    }}


