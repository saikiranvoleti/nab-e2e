package listener;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.annotations.*;
import utils.CucumberReporter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@CucumberOptions(
        extraGlue = {"listener"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        }
)
public class CucumberBaseTest implements ITest, ITestNGListener {
        private static final Logger logger = LogManager.getLogger();

        private TestNGCucumberRunner testNGCucumberRunner;
        private final ThreadLocal<String> scenarioName = new ThreadLocal<>();
        private static DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss_a");
        public static String reportFolderPath;
        public static ThreadLocal<Scenario> scenarios=new ThreadLocal<>();
        @BeforeClass(alwaysRun = true)
        public void setUpClass() {

            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
            Calendar cal = Calendar.getInstance();
            String currentDir = System.getProperty("user.dir");
            String date=dateFormat.format(cal.getTime());
            reportFolderPath=currentDir+"/reports/cucumber_report_"+date;
            File directory = new File(reportFolderPath);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    logger.info ("File created: " + reportFolderPath);
                }

            }
        }
        @BeforeMethod(alwaysRun = true)
        public void setTestName(Method method, Object[] testData) {
            Pickle pickle = ((PickleWrapper) testData[0]).getPickle();
            String scenarioName = pickle.getName();
            this.scenarioName.set(scenarioName);
            logger.info("START {}", scenarioName);
        }

        @Test(dataProvider = "scenarios")
        public void scenario(PickleWrapper pickle, FeatureWrapper fw) throws Throwable {
            testNGCucumberRunner.runScenario(pickle.getPickle());
        }


    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

        @AfterMethod
        public void afterMethod(ITestResult result) {

            logResult(result);
        }

        private void logResult(ITestResult result) {
            switch (result.getStatus()) {
                case ITestResult.FAILURE:
                    logger.error("FAIL  {}", scenarioName.get());
                    break;
                case ITestResult.SKIP:
                    logger.warn("SKIP  {}", scenarioName.get());
                    // scenarioName.set(scenarioName+"_Retry_"+RetryAnalyzer.getRetryCount());
                    break;
                case ITestResult.SUCCESS:
                    logger.info("PASS  {}", scenarioName.get());
                    break;
                default:
                    logger.warn("Unexpected result status: {}", result.getStatus());
            }
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() {
            testNGCucumberRunner.finish();
            CucumberReporter.generateReport();
        }

        @Override
        public String getTestName() {
            return scenarioName.get();
        }

        @Before
        public void beforeScenario(Scenario scenario){
            scenarios.set(scenario);
        }

    }


