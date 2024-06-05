package example.config;

import com.bottlerocket.config.ResourceLocatorBundle;
import com.bottlerocket.webdriverwrapper.uiElementLocator.LocatorStrategy;
import com.bottlerocket.webdriverwrapper.uiElementLocator.TestPlatform;
import com.bottlerocket.webdriverwrapper.uiElementLocator.UIElementLocator;
import com.bottlerocket.webdriverwrapper.uiElementLocator.UIElementSelector;
import org.openqa.selenium.By;

/**
 * Probably needs a more accurate name
 * <p>
 * This class keeps all constants (names, key values, resource locations, etc.) which Appium needs either to find a resource or send a command to the device
 * <p>
 * <p>
 * {@link automationtests.testingautomationtests.ResourceLocatorExamples Test with example usage}
 * <p>
 * Created by ford.arnett on 8/31/15.
 */
public class ResourceLocator {
    public static config.ResourceLocator device;

    /*
     * ResourceLocatorBundle example code
     */

    //Use ResourceLocatorBundle to quickly define a locator strategy for each platform and select the correct locator strategy at runtime
    // ************* NOTE THE NAMING CONVENTION. PRODUCT_PAGE_NAME_OF_LOCATOR ****************
    public static final ResourceLocatorBundle GOOGLE_HOME_INPUT_TEXT_BOX_GOOGLE_SEARCH = ResourceLocatorBundle.build(By.xpath("//input[@name='q']"), By.id("iOS"), By.className("android"));

    public static final ResourceLocatorBundle TEST_BUNDLE_STRING = ResourceLocatorBundle.build("webStringElement", "//XCUIElementTypeStaticText[@name='Sign In']", "//android.view.View[@content-desc='Sign In']");

    public static final ResourceLocatorBundle SAUCE_INVENTORY_ADD_BIKE_LIGHT_TO_CART = ResourceLocatorBundle.build(By.id("add-to-cart-sauce-labs-bike-light"));

    public static final ResourceLocatorBundle SAUCE_INVENTORY_ITEM_BACK_TO_PRODUCTS = ResourceLocatorBundle.build(By.id("back-to-products"));

    public static final ResourceLocatorBundle SAUCE_INVENTORY_ITEM_SHOPPING_CART_CONTAINER = ResourceLocatorBundle.build(By.id("shopping_cart_container"));


    /**
     * UIElement example code
     */
    public ResourceLocator() {
        //attach all UIElementSelectors to their UIElementLocators
        //NOTE: setElementLocators() does not need to be used here if NOT using UIElementLocator strategy
        setElementLocators();
    }

    //UIElementLocator strategy = define a UIElementLocator and add UIElementSelectors to it


    //Define any other standard variables
    public static final String URL_GOOGLE = "https://www.google.com/";


    //Use UIElement locator for more flexible locator strategies
    public static final UIElementLocator INPUT_BUTTON_GOOGLE_SEARCH = new UIElementLocator();
    UIElementSelector INPUT_BUTTON_GOOGLE_SEARCH_WEB_XPATH = new UIElementSelector(TestPlatform.WEB, LocatorStrategy.XPATH, "(//input[@name='btnK' and @type='submit'])[2]");

    //Attach all UIElementSelectors to their respective UIElementLocators
    private void setElementLocators() {
        INPUT_BUTTON_GOOGLE_SEARCH.addElementSelector(INPUT_BUTTON_GOOGLE_SEARCH_WEB_XPATH);
        INPUT_BUTTON_GOOGLE_SEARCH.addElementSelector(TestPlatform.ANDROID, LocatorStrategy.ID, "myID");
        this.INPUT_BUTTON_GOOGLE_SEARCH_WEB_XPATH.setTag("button");


    }

}
