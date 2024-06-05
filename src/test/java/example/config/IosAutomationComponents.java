package example.config;

import com.bottlerocket.config.ResourceLocatorBundle;
import com.bottlerocket.utils.InputUtils;
import com.bottlerocket.utils.InputUtilsIos;
import com.bottlerocket.webdriverwrapper.AppiumDriverWrapper;
import example.assertions.AssertionLibrary;
import example.assertions.AssertionLibraryIos;
import example.operations.UserOperations;
import example.operations.UserOperationsIos;
import example.operations.navops.NavigationOperations;
import example.operations.navops.NavigationOperationsIos;

/**
 * Implementation of the strategy pattern, contains components of the system
 * <p>
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
        return new InputUtilsIos(driverWrapper);
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
