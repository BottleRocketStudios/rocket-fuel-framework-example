package config;

import automationtests.assertions.AssertionLibrary;
import com.bottlerocket.webdriverwrapper.AppiumDriverWrapper;
import com.bottlerocket.utils.InputUtils;
import operations.UserOperations;
import operations.navops.NavigationOperations;

/**
 * Created by ford.arnett on 10/29/15.
 */
public interface DeviceAutomationComponents {
    NavigationOperations getNavigationOperations();

    UserOperations getUserOperations();

    void initResourceLocator();

    InputUtils createInputUtils(AppiumDriverWrapper driverWrapper);

    AssertionLibrary getAssertions();
}
