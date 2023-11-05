package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.WebDriverFactory;

public class Hooks {
    @Before
    public void setup(Scenario scenario){
        WebDriverFactory.getDriver();

    }
    @After
    public void cleanup(){
        WebDriverFactory.quitDriver();

    }


}
