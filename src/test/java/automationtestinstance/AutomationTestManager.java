/*
  Created by ford.arnett on 10/6/21
 */
package automationtestinstance;

import automationtests.assertions.*;
import com.bottlerocket.config.*;
import com.bottlerocket.reporters.*;
import com.bottlerocket.webdriverwrapper.*;
import config.*;
import io.appium.java_client.service.local.*;
import operations.*;
import operations.navops.*;
import java.util.*;

public class AutomationTestManager {
    public DeviceAutomationComponents deviceAutomationComponents;

    public UserOperations userOp;
    public NavigationOperations navOp;
    public AutomationConfigProperties config;
    public AssertionLibrary assertions;
    public AutomationReporter reporter;
    public AppiumDriverLocalService appiumService;
    public WebDriverWrapper driverWrapper;
    public List<String> stepList;
    public Steps steps;


    /**
     * Call the init methods for all of the class variables for {@link AutomationTestManager} so that the objects are initialized
     *
     */
    public void initializeComponents() {
        driverWrapper.setAutomationReporter(this.reporter);
        userOp.init(this);
        navOp.init(this);
        assertions.init(this);
        steps = new Steps(this);
        stepList = new ArrayList<>();
    }
}

