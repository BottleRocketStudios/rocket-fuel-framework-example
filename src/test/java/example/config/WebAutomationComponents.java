package example.config;

import com.bottlerocket.config.ResourceLocatorBundle;
import com.bottlerocket.utils.InputUtils;
import com.bottlerocket.webdriverwrapper.AppiumDriverWrapper;
import example.assertions.AssertionLibrary;
import example.assertions.AssertionLibraryWeb;
import example.operations.UserOperations;
import example.operations.UserOperationsWeb;
import example.operations.navops.NavigationOperations;
import example.operations.navops.NavigationOperationsWeb;

/**
 * Created by ford.arnett on 8/10/18
 */
public class WebAutomationComponents implements DeviceAutomationComponents {

    @Override
    public NavigationOperations getNavigationOperations() {
        return new NavigationOperationsWeb();
    }

    @Override
    public UserOperations getUserOperations() {
        return new UserOperationsWeb();
    }

    @Override
    public void initResourceLocator() {
        ResourceLocatorBundle.runtimePlatform = "web";
    }

    @Override
    public InputUtils createInputUtils(AppiumDriverWrapper driverWrapper) {
        return null;
    }

    @Override
    public AssertionLibrary getAssertions() {
        return new AssertionLibraryWeb();
    }


}
