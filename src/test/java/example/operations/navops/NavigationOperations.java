package example.operations.navops;


import com.bottlerocket.config.ResourceLocatorBundle;
import com.bottlerocket.webdriverwrapper.WebDriverWrapperGeneric;
import example.automationtestinstance.AutomationTestManager;
import example.operations.TestInitializerListener;
import org.openqa.selenium.By;


/**
 * Handles behaviors that don't belong to a single screen, but may not need their own navOps, like a nav bar.
 * <p>
 * Created by ford.arnett on 9/3/15.
 */
public abstract class NavigationOperations implements TestInitializerListener {

    AutomationTestManager am;

    //NavOps classes here
    public NavOpsInventory inventory = new NavOpsInventory();
    public NavOpsLogIn login = new NavOpsLogIn();


    @Override
    public void init(AutomationTestManager am) {
        this.am = am;

        //init nav classes here
        inventory.init(am);
        login.init(am);

    }

    public void goToURL(String url) {
        ((WebDriverWrapperGeneric) am.driverWrapper).get(url);
    }

    public void clickBy(ResourceLocatorBundle rlb) {
        am.driverWrapper.getElement(rlb.getBy()).click();
    }


    public void clickToNavByID(String id) {
        am.driverWrapper.getElement(By.xpath(id)).click();
    }

    /**
     * Assertion Checks
     */

    public boolean verifyContentIsPresentById(String resourceId) {
        return am.driverWrapper.elementExists(By.id(resourceId));
    }

    public boolean verifyContentIsPresentBy(By by) {
        return am.driverWrapper.elementExists(by);
    }


    public boolean isErrorVisible() {
        return am.driverWrapper.getElement(By.className("error-button")).isDisplayed();
    }
}

