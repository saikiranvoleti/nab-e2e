package DPStepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.sl.In;
import io.cucumber.java8.En;
import org.testng.Assert;
import pages.*;
import utils.FileWriterUtil;
import utils.ReporterLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class DPStepDefs implements En {

    HomePage homePage;

    public DPStepDefs() {

        Given("User is on the Home page", () -> {
            homePage=new HomePage();
            homePage.isLoaded();

        });

        And("Find all the footer links and save to file {string}", (String fileName) -> {
        try{
            List<String> data =new ArrayList<>();
            String heading ="Title\tLink";
            FileWriterUtil util=new FileWriterUtil();
            homePage.getFooterLinks().forEach((k,v)->{
                String val = k + "\t" + v ;
                data.add(val);
            });
            util.writeToFile(fileName,heading,data);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        });

    }

}
