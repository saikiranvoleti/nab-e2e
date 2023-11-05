import io.cucumber.testng.CucumberOptions;
import listener.CucumberBaseTest;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "DPStepDefs",
        tags = "@footer"
)

public class CucumberRunner extends CucumberBaseTest {

}