package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.Base;

import java.util.ArrayList;
import java.util.List;

public class NewsPage extends Base {

    @FindBy(css = "div[data-testid='heading']")
    WebElement heading;

    private final By videoFeeds= By.xpath("//div[div[h3[text()='VIDEOS']]]//div[@data-testid='tile-article']");
    private final By title= By.cssSelector("h3.tile-article-title");
    private final By date= By.cssSelector("div[data-testid='tile-meta'] time");
    private final By timeSpan= By.cssSelector("div[data-testid='tile-meta'] time span");
    private final By duration= By.cssSelector("div[data-testid='post-duration']");
    protected By excerpt=By.cssSelector("p.tile-article-excerpt");

    @Override
    public boolean isLoaded() {
    return wait.until(e->heading.isDisplayed());
    }


    public List<VideoFeedDetails> getVideoFeedDetails(){
        List<VideoFeedDetails> productsList = new ArrayList<>();

        List<WebElement> feeds = driver.findElements(videoFeeds);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(videoFeeds));
        for (WebElement productElement : feeds) {
            String videoTitle = productElement.findElement(title).getText().trim();
            String videoDate = productElement.findElement(date).getAttribute("datetime").trim();
            String videoTimeSpan =productElement.findElement(timeSpan).getText().trim();
            String videoDuration =productElement.findElement(duration).getText().trim();

            VideoFeedDetails product = new VideoFeedDetails(videoTitle, videoDate, videoTimeSpan,videoDuration);
            productsList.add(product);
        }

        return productsList;

    }

}
