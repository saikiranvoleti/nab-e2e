import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listener.CucumberBaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.WebDriverFactory;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefs",
        tags = "@core_product"
)

public class CucumberRunner extends CucumberBaseTest {

}