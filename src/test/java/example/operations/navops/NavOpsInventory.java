package example.operations.navops;

import config.ResourceLocator;
import example.automationtestinstance.AutomationTestManager;
import example.operations.TestInitializerListener;
import org.openqa.selenium.By;

/**
 * Navigation Operations for Inventory screen go here. These are actions to be performed on the Inventory screen.
 * Created by ford.arnett on 5/26/23
 */
public class NavOpsInventory implements TestInitializerListener {
    AutomationTestManager am;

    @Override
    public void init(AutomationTestManager am) {
        this.am = am;
    }

    public String getProductPrice(int index) {
        return am.driverWrapper.getElements(ResourceLocator.SAUCE_INVENTORY_PRODUCT_PRICE).get(index).getText();
    }
}
