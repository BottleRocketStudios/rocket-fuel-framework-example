package example;

import example.assertions.AssertionPayload;
import example.assertions.AssertionCategories;
import example.testmain.TestMainExample;
import org.testng.annotations.Test;

/**
 * Welcome to the hands-on examples. They are designed to help you learn the basics of automation testing with the Rocket Fuel Framework.
 * <p>
 * Created by ford.arnett on 5/26/23
 */

public class ExerciseOneTestCase extends TestMainExample {

    /**
     * This is the first exercise. It will navigate to the Sauce Demo page and attempt to log in with no credentials.
     * <p>
     * A few parts of this test:
     * User Operations
     * Navigation Operations
     * AssertionPayload
     * <p>
     * Every test will have Navigation Operations to set up the test, and AssertionPayload to assert your test and capture test results.
     * Many tests, but not all, will have User Operations to perform actions to capture test data, or a set of actions ancillary to
     * what is being tested, but necessary for the test. This could be something such as logging in before testing an order flow,
     * or putting items in the cart before testing the checkout screen in a checkout flow. These all tools to help structure your tests and
     * make them more readable and maintainable. If you find a different flow that works for you, we encourage you to go with it (and share it with us!).
     * <p>
     * In this test we use userOps(User Operations) to add information to the report and take a screenshot.
     * We also use navOps (Navigation Operations) to navigate to the Sauce Demo page and click the login button.
     * Finally, we use assertions to verify that the error message pops up under a failed login.
     */
    @Test(groups="smoke", description = "verify that a user with no credentials will see an error message when they try to log in")
    public void exerciseOne() {
        am.navOp.goToURL("https://www.saucedemo.com");

        // Grab the URL currently being displayed in active session and store into a variable and print in the report
        String sauceDemoURL = am.driverWrapper.getCurrentURL();
        am.userOp.addInfoToReport("The current URL is " + sauceDemoURL);
        am.userOp.takeScreenshot("sauce_demo_page");

        am.navOp.login.clickLogin();

        // Verify we can't log in with no credentials and error message shows
        am.assertions.generalAssertion(new AssertionPayload(am.navOp.isErrorVisible(), "Verify error message pops up under failed login",
                AssertionCategories.Login));
    }

    @Test(groups="smoke", description = "verify that a user with no credentials will see an error message when they try to log in")
    public void exerciseOneTest2() {
        am.navOp.goToURL("https://www.saucedemo.com");

        // Grab the URL currently being displayed in active session and store into a variable and print in the report
        String sauceDemoURL = am.driverWrapper.getCurrentURL();
        am.userOp.addInfoToReport("The current URL is " + sauceDemoURL);
        am.userOp.takeScreenshot("sauce_demo_page");

        am.navOp.login.clickLogin();

        // Verify we can't log in with no credentials and error message shows
        am.assertions.generalAssertion(new AssertionPayload(am.navOp.isErrorVisible(), "Verify error message pops up under failed login",
                AssertionCategories.Login));
    }

}