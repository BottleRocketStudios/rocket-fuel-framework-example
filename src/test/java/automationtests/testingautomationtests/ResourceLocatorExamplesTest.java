package automationtests.testingautomationtests;

import com.bottlerocket.webdriverwrapper.uiElementLocator.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import testmain.*;

import java.net.*;

import static config.ResourceLocator.*;

public class ResourceLocatorExamplesTest extends TestMain {

    /*
    QUICK UI Test to display ResourceLocator usage
    URL_GOOGLE: standard String stored in ResourceLocator
    INPUT_TEXT_BOX_GOOGLE_SEARCH: By from ResourceLocatorBundle
    INPUT_BUTTON_GOOGLE_SEARCH: By from UIElementLocator
     */
    @Test(groups={"smoke","regression"})
    public void resourceLocatorExamples() throws MalformedURLException {

        URL url = new URL(URL_GOOGLE);
        setupNextTest(url);

        /*
         * Retrieving locators using ResourceBundle.
         */
        //Option 1: Use it directly
        am.driverWrapper.getElement(INPUT_TEXT_BOX_GOOGLE_SEARCH.getBy(), 5).sendKeys("what do people google?");

        setupNextTest(url);

        //Option 2: Store in a By
        By input_google_search = INPUT_TEXT_BOX_GOOGLE_SEARCH.getBy();
        am.driverWrapper.getElement(input_google_search, 5).sendKeys("what do people google?");

        setupNextTest(url);

        //Option 3: Using string instead of By
        am.driverWrapper.getElement(TEST_BUNDLE_STRING).click();

        /*
         * Retrieving locators using UIElementLocator.
         */
        //Option 1
        //TODO: Update UIElementor locater (is not working atm)
//        By input_button_google_search = INPUT_BUTTON_GOOGLE_SEARCH.getLocator(LocatorStrategy.XPATH);
//        am.driverWrapper.getElement(input_button_google_search, 5).click();
//
//        setupNextTest(url);
//
//        //Option 2
//        By input_button_google_search_1 = INPUT_BUTTON_GOOGLE_SEARCH.getLocator("button");
//        am.driverWrapper.getElement(input_button_google_search_1, 5).click();
//
//        setupNextTest(url);
    }

    private void setupNextTest(URL url) {
        am.driverWrapper.navigateTo(url);
    }
}
