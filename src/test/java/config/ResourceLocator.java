package config;

import automationtests.testingautomationtests.ResourceLocatorExamplesTest;
import com.bottlerocket.config.*;
import com.bottlerocket.webdriverwrapper.uiElementLocator.*;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;

/**
 * Probably needs a more accurate name
 * <p>
 * This class keeps all constants (names, key values, resource locations, etc.) which Appium needs either to find a resource or send a command to the device
 * <p>
 * <p>
 * {@link ResourceLocatorExamplesTest Test with example usage}
 * <p>
 * Created by ford.arnett on 8/31/15.
 */
public class ResourceLocator {
    public static ResourceLocator device;

    /*
     * ResourceLocatorBundle example code
     */

    //Use ResourceLocatorBundle to quickly define a locator strategy for each platform and select the correct locator strategy at runtime
    public static final ResourceLocatorBundle INPUT_TEXT_BOX_GOOGLE_SEARCH = ResourceLocatorBundle.build(By.xpath("//*[@name='q']"), By.id("iOS"), By.className("android"));

    public static final ResourceLocatorBundle TEST_BUNDLE_STRING = ResourceLocatorBundle.build(By.xpath("//*[@name='q']"), By.xpath("//XCUIElementTypeStaticText[@name='Sign In']"), By.xpath("//android.view.View[@content-desc='Sign In']"));

    public static final ResourceLocatorBundle IOS_ANDROID_ONLY_EXAMPLE = ResourceLocatorBundle.build(By.xpath("//*[@name='iOS element']"), By.className("Android_className"));

    // Useful for getting the string name or desc of a RLB element e.g. String s = getElement(rlb).getAttribute(NAME_OR_DESC_ATTRIBUTE);
    public static final ResourceLocatorBundle NAME_OR_DESC_ATTRIBUTE = ResourceLocatorBundle.build("name", "content-desc");

    //Login page
    public static final ResourceLocatorBundle SAUCE_LOGIN_LOGIN_BUTTON = ResourceLocatorBundle.build(By.id("login-button"));
    public static final ResourceLocatorBundle SAUCE_LOGIN_USERNAME = ResourceLocatorBundle.build(By.id("user-name"));
    public static final ResourceLocatorBundle SAUCE_LOGIN_PASSWORD = ResourceLocatorBundle.build(By.id("password"));

    //Inventory page
    public static final ResourceLocatorBundle SAUCE_INVENTORY_INVENTORY_CONTAINER = ResourceLocatorBundle.build(By.id("inventory_container"));
    public static final ResourceLocatorBundle SAUCE_INVENTORY_BIKE_LIGHT = ResourceLocatorBundle.build(By.xpath("(//img[@alt='Sauce Labs Bike Light'])"));
    public static final ResourceLocatorBundle SAUCE_INVENTORY_PRODUCT_PRICE = ResourceLocatorBundle.build(By.className("inventory_item_price"));


    // Helpful RLB build methods for Flutter projects
    public static ResourceLocatorBundle buildAccessibilityIdRLB(String id) {
        return ResourceLocatorBundle.build(new AppiumBy.ByAccessibilityId(id), new AppiumBy.ByAccessibilityId(id));
    }

    public static ResourceLocatorBundle buildXpathExactRLB(String text) {
        By iOS = By.xpath(String.format("//*[@name='%s']", text));
        By android = By.xpath(String.format("//*[@content-desc='%s']", text));
        return ResourceLocatorBundle.build(iOS, android);
    }

    public static ResourceLocatorBundle buildXpathContainsRLB(String text) {
        By iOS = By.xpath(String.format("//*[contains(@name,'%s')]", text));
        By android = By.xpath(String.format("//*[contains(@content-desc,'%s')]", text));
        return ResourceLocatorBundle.build(iOS, android);
    }

    public static ResourceLocatorBundle buildXpathStartsWithRLB(String text) {
        By iOS = By.xpath(String.format("//*[starts-with(@name,'%s')]", text));
        By android = By.xpath(String.format("//*[starts-with(@content-desc,'%s')]", text));
        return ResourceLocatorBundle.build(iOS, android);
    }

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
