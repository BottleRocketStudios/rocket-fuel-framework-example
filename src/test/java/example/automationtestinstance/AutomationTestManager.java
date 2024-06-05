/*
  Created by ford.arnett on 10/6/21
 */
package example.automationtestinstance;

import example.assertions.AssertionLibrary;
import com.bottlerocket.config.AutomationConfigProperties;
import com.bottlerocket.reporters.AutomationReporter;
import com.bottlerocket.webdriverwrapper.WebDriverWrapper;
import config.DeviceAutomationComponents;
import example.operations.UserOperations;
import example.operations.navops.NavigationOperations;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AutomationTestManager {
    public DeviceAutomationComponents deviceAutomationComponents;

    public UserOperations userOp;
    public NavigationOperations navOp;
    public AutomationConfigProperties config;
    public AssertionLibrary assertions;
    public AutomationReporter reporter;
    public AppiumDriverLocalService appiumService;
    public WebDriverWrapper driverWrapper;


    /**
     * Call the init methods for all of the class variables for {@link AutomationTestManager} so that the objects are initialized
     */
    public void initializeComponents() {
        driverWrapper.setAutomationReporter(this.reporter);
        userOp.init(this);
        navOp.init(this);
        assertions.init(this);
    }
}

