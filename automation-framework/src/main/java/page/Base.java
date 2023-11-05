package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.WebActions;


public abstract class Base extends WebActions
{
    private static final Logger logger = LogManager.getLogger();

    protected String windowHandle=null;
    protected Base(){
        super();

        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);

    }

    public abstract boolean isLoaded();


}
