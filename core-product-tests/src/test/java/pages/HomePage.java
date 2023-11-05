package pages;

import config.PropertiesConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.Base;
import utils.ScreenshotUtil;

import java.util.List;

public class HomePage extends Base {

    private  final String url =  PropertiesConfig.URL.get();
    @FindBy(css = "div#nba-nav")
    private WebElement nav;
    @FindBy(css = "div.brand-font ul[role=menubar]>li span")
    private List<WebElement> primary_menu;

    @FindBy(css = "div.brand-font ul>li[role='menuitem']>a")
    private List<WebElement> sub_menus;
    @FindBy(css = "div[role=dialog]")
    private WebElement dialog;

    @FindBy(css = "div.flex-end div")
    private WebElement closeDialog;

    @FindBy(css = "button#onetrust-accept-btn-handler")
    private WebElement cookieAcceptBtn;

    @FindBy(css = "div[data-testid='display-ad']")
    private WebElement background;

    @Override
    public boolean isLoaded() {
        try {
            driver.navigate().to(url);
            wait.until(e->this.dialog.isDisplayed());
            closeDialog.click();
            wait.until(e->this.cookieAcceptBtn.isDisplayed());
            cookieAcceptBtn.click();
            wait.until(ExpectedConditions.visibilityOf(background));
            Thread.sleep(10000);
            return this.wait.until((d) -> this.nav.isDisplayed());

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void navigateTo(String menu,String submenu){
try{
        ScreenshotUtil.takeScreenshot();
        primary_menu.stream().filter(d -> d.getText().trim().equalsIgnoreCase(menu))
                        .findFirst().ifPresent(e->{
                            waitForElement(e);
                            actions.moveToElement(e).perform();});

        sub_menus.stream().filter(e -> e.getText().trim().equalsIgnoreCase(submenu))
                .findFirst().ifPresent(e->{
                    waitForElement(e);
                    clickElementWithJavaScript(e);
                });

        }
catch(Exception ex){
        ex.printStackTrace();
        }



}}


