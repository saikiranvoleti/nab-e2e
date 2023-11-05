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
        And("^Verify the submenu items for menu \"([^\"]*)\"$", (String menuItem) -> {
            List<String> submenus=homePage.getSubMenuItems(menuItem);
            ReporterLog.lotText("below are the sub menu items under "+menuItem);
            submenus.forEach(ReporterLog::lotText);
        });
        And("Verify duration for below slides", (DataTable table) -> {

            List<Map<String, String>> data = table.asMaps(String.class, String.class);

            for (Map<String, String> row : data) {
                String name = row.get("slideName");
                int expDur = Integer.parseInt(row.get("expDuration"));
                long duration = homePage.getSlideDuration(name);
                ReporterLog.lotText("Slide Name= " + name + "; duration= " + duration + "; expected duration" + expDur);
                Assert.assertTrue(duration<= expDur);
            }
        });

    }

}
