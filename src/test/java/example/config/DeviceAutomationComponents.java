package example.config;

import com.bottlerocket.utils.InputUtils;
import com.bottlerocket.webdriverwrapper.AppiumDriverWrapper;
import example.assertions.AssertionLibrary;
import example.operations.UserOperations;
import example.operations.navops.NavigationOperations;

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
