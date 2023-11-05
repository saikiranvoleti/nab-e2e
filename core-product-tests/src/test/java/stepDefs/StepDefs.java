package stepDefs;

import io.cucumber.java8.En;
import org.testng.Assert;
import pages.*;
import utils.FileWriterUtil;
import utils.ReporterLog;

import java.util.ArrayList;
import java.util.List;

public class StepDefs implements En {

    HomePage homePage;
    SearchPage searchPage;
    NewsPage newsPage;

    public StepDefs() {
        And("Navigate to menu {string} and submenu {string}", (String menu, String submenu) -> {

            homePage.navigateTo(menu,submenu);
        });
        Given("User is on the Home page", () -> {
            homePage=new HomePage();
            homePage.isLoaded();

        });
        And("^User is on Search Page$", () -> {
            searchPage=new SearchPage();
            searchPage.isLoaded();

        });
        And("Navigate to menu {string} and submenu {string} on Search Page", (String menu, String submenu) -> {
            searchPage.navigateTo(menu,submenu);
        });

        And("Select filter {string} on Search Page", (String filterName) -> {
            searchPage.selectFilter(filterName);
        });
        And("Store Products information in a file {string}", (String fileName) -> {
            List<ProductDetails> products =searchPage.getProductDetails();
            List<String> data =new ArrayList<>();
            String heading ="Title\tPrice\tMessage";
            FileWriterUtil util=new FileWriterUtil();
            for (ProductDetails pds:products
                 ) {
                String val = pds.getTitle() + "\t" + pds.getPrice() + "\t" + pds.getMessage();
                data.add(val);
            }
            util.writeToFile(fileName,heading,data);
        });

        Given("user is on the News and Features Page", () -> {
            newsPage=new NewsPage();
            newsPage.isLoaded();
        });
        Then("^Verify the video feed count is (\\d+)$", (Integer count) -> {
            List<VideoFeedDetails> details = newsPage.getVideoFeedDetails();
            ReporterLog.lotText("Total No. of video feed found "+details.size());
            ReporterLog.lotText("Expected No. of video feed found  "+count);
            Assert.assertEquals(details.size(),count);
        });
        Then("^Verify the video feed count greater than (\\d+) days$", (Integer count) -> {
           List<VideoFeedDetails> details = newsPage.getVideoFeedDetails();
           long feedCount =details.stream().map(VideoFeedDetails::getTimeSpan).filter(e->e.contains("d"))
            .map(e->
                    e.replace("d","")
            ).map(Integer::parseInt).filter(e->e>=count).toList().size();
           ReporterLog.lotText("Total Video Feeds "+details.size());
            ReporterLog.lotText("Video Feeds greater than "+count+" days is "+feedCount);


        });
    }

}
