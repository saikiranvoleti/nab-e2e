package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchPage extends Base {

    @FindBy(css = "form[role='search']>input#typeahead-input-desktop")
    private WebElement searchbox;

    @FindBy(css = "ul[role='menu']>li.top-nav-item>a")
    private List<WebElement> primary_menu;

    @FindBy(css = "a[class=dropdown-link][role='menuitem']>div.dropdown-link-text")
    private List<WebElement> sub_menus;

    @FindBy(css = "a[data-talos='filter']")
    private List<WebElement> filters;
    private By products= By.cssSelector("div.product-card");
    private By productPrice= By.cssSelector("div.price-card span.sr-only");
    private By productTitle= By.cssSelector("div.product-card-title>a");
    protected By productMessage= By.cssSelector("span.top-seller-vibrancy-message");

    @FindBy(css = "div.product-grid-top-area div.page-list a[data-talos='linkSearchResultsPage']")
    private List<WebElement> pages;
    protected By nextPage= By.cssSelector("div.product-grid-top-area a[aria-label='next page']");

    @Override
    public boolean isLoaded() {
        try {
            String currentWindowHandle = driver.getWindowHandle();
            Set<String> allWindowHandles = driver.getWindowHandles();
            for (String windowHandle : allWindowHandles) {
                if (!windowHandle.equals(currentWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            windowHandle=currentWindowHandle;
        return wait.until(e->this.searchbox.isDisplayed());
        }
        catch(Exception ex){
        ex.printStackTrace();
    }
        return false;
    }
    public void navigateTo(String menu,String submenu) {
        try {
            primary_menu.stream().filter(d -> d.getText().trim().equalsIgnoreCase(menu))
                    .findFirst().ifPresent(e->{
                        waitForElement(e);
                        actions.moveToElement(e).perform();});

            sub_menus.stream().filter(e -> e.getText().trim().equalsIgnoreCase(submenu))
                    .findFirst().ifPresent(b->{
                        actions.moveToElement(b).doubleClick(b).perform();
                    });



        }
catch(Exception ex){
        ex.printStackTrace();
    }
    }

    public void selectFilter(String name){

        filters.stream().filter(e->e.getAttribute("data-trk-id").equalsIgnoreCase(name))
                .findFirst().ifPresent(WebElement::click) ;
    }

    public List<ProductDetails> getProductDetails(){
        List<ProductDetails> productsList = new ArrayList<>();
        for (int i=0;i<pages.size()-1;i++){
            List<WebElement> productElements = driver.findElements(products);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(products));
        for (WebElement productElement : productElements) {
            String title = productElement.findElement(productTitle).getText().trim();
            String price = productElement.findElement(productPrice).getText().trim();
            String message ="";
            if(productElement.findElements(productMessage).size()>0){
                 message = productElement.findElement(productMessage).getText().trim();
            }
            ProductDetails product = new ProductDetails(title, price, message);
            productsList.add(product);
        }
        WebElement next = driver.findElement(nextPage);
        if(next.getAttribute("aria-disabled").equalsIgnoreCase("false")){
            next.click();
        }
        }
        return productsList;

    }

}
