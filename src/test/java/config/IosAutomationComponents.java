package config;

import automationtests.assertions.AssertionLibrary;
import automationtests.assertions.AssertionLibraryIos;
import com.bottlerocket.config.*;
import com.bottlerocket.webdriverwrapper.*;
import com.bottlerocket.utils.InputUtils;
import com.bottlerocket.utils.InputUtilsIos;
import operations.UserOperations;
import operations.navops.NavigationOperations;
import operations.navops.NavigationOperationsIos;
import operations.UserOperationsIos;

/**
 * Implementation of the strategy pattern, contains components of the system
 *
 * Created by ford.arnett on 10/29/15.
 */
public class IosAutomationComponents implements DeviceAutomationComponents {

    @Override
    public NavigationOperations getNavigationOperations() {
        return new NavigationOperationsIos();
    }

    @Override
    public UserOperations getUserOperations() {
        return new UserOperationsIos();
    }

    @Override
    public InputUtils createInputUtils(AppiumDriverWrapper driverWrapper) {
        return new InputUtilsIos( driverWrapper);
    }

    @Override
    public void initResourceLocator() {
        ResourceLocatorBundle.runtimePlatform = "ios";
        return;
    }

    @Override
    public AssertionLibrary getAssertions() {
        return new AssertionLibraryIos();
    }
}
