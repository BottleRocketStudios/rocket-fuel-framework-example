package operations;

import automationtestinstance.AutomationTestManager;

/**
 *
 * Created by ford.arnett on 9/3/15.
 */
public abstract class UserOperations implements TestInitializerListener {
    AutomationTestManager am;

    @Override
    public void init(AutomationTestManager am) {
        this.am = am;
    }

    public void takeScreenshot(String fileName) {
        //This is temporary, when running on sauce this is erroring and killing the logs
        if (am.config.remote == true) {
            am.driverWrapper.takeScreenshotSuppressError(System.getProperty("user.dir") + "/extent_reports/mobile_screenshots/", fileName + "_" + System.currentTimeMillis() + ".png", "mobile_screenshots/");
        } else {
            am.driverWrapper.takeScreenshotSuppressError(am.config.screenshotsDirectory, fileName + "_" + System.currentTimeMillis() + ".png", "mobile_screenshots/");
        }
    }

    public void waitLogErr(int millis) {
        am.driverWrapper.waitLogErr(millis);
    }
    
}
