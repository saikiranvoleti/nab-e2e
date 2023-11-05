package utils;

import listener.CucumberBaseTest;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CucumberReporter {

    public static void generateReport(){
        String currentDir = System.getProperty("user.dir");
        File reportOutputDirectory = new File(CucumberBaseTest.reportFolderPath);
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(currentDir+"/target/cucumber-reports/cucumber.json");
        String buildNumber = "1";
        String projectName = "nba e2e";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
// optional configuration - check javadoc for details
        configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
// do not make scenario failed when step has status SKIPPED
        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
        configuration.setBuildNumber(buildNumber);
// addidtional metadata presented on main page
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Branch", "release/1.0");

 //optionally add metadata presented on main page via properties file
        List<String> classificationFiles = new ArrayList<>();

        classificationFiles.add(currentDir+"/src/test/resources/config.properties");
        configuration.addClassificationFiles(classificationFiles);

// optionally specify qualifiers for each of the report json files
        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
        configuration.setQualifier("cucumber","First report");

        ReportBuilder reportBuilder=new ReportBuilder(jsonFiles,configuration);
        Reportable result=reportBuilder.generateReports();

    }


}
