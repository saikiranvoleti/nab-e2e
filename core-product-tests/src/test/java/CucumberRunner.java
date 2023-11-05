import io.cucumber.testng.CucumberOptions;
import listener.CucumberBaseTest;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefs",
        tags = "@news"
)

public class CucumberRunner extends CucumberBaseTest {

}