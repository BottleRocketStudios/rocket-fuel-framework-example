package example;


import domod.User;
import example.assertions.AssertionCategories;
import example.assertions.AssertionPayload;
import example.testmain.TestMainExample;
import org.testng.annotations.Test;

import static config.ResourceLocator.SAUCE_INVENTORY_INVENTORY_CONTAINER;

public class ExerciseTwoTestCase extends TestMainExample {

    /**
     * One new concept introduced in this exercise is the use of the test data manager.
     * Storing data in the test data manager is
     * useful for sharing data between tests or easily changing environment data between runs.
     * <p>
     * You can change the data in {@link config.TestDataManager}. We recommend creating your TestDataManager object in your
     * base test class, and then using it in your test classes, as we've done here. To change environment data,
     * simply change the inputs to your TestDataManger constructor in your base test class. Of course, you can
     * also use a different user per test class if you that works better for your use case.
     */
    @Test(groups={"regression"}, description = "Verify user with valid credentials can log in")
    public void exerciseTwo() {
        //This is the test data manager. See method documentation for more information.
        User sauceUser = testDataManager.activeUser;

        am.navOp.goToURL("https://www.saucedemo.com");

        // Grab the URL currently being displayed in active session and store into a variable and print in the report
        String sauceDemoURL = am.driverWrapper.getCurrentURL();
        am.userOp.addInfoToReport("The current URL is " + sauceDemoURL);
        am.userOp.takeScreenshot("<b> Sauce demo page </b>");

        // Enter Username
        am.navOp.login.enterUsername(sauceUser.username);

        // Enter Password
        am.navOp.login.enterPasswords(sauceUser.password);

        // Select Login button
        am.navOp.login.clickLogin();

        // Validate that log in was successful and currently on Product Listing page.
        am.assertions.generalAssertion(new AssertionPayload(am.navOp.verifyContentIsPresentBy(SAUCE_INVENTORY_INVENTORY_CONTAINER.getBy()),
                "Log in was successful and currently on product listing page",
                AssertionCategories.Inventory));

        String price = "$29.99";
        am.assertions.generalAssertion(new AssertionPayload(price.equals(am.navOp.inventory.getProductPrice(0)),
                "Verify First Product Price is " + price,
                AssertionCategories.Inventory));
    }

}