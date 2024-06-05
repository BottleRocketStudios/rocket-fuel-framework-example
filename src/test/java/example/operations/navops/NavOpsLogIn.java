package example.operations.navops;

import example.automationtestinstance.AutomationTestManager;
import example.operations.TestInitializerListener;
import org.openqa.selenium.By;

import static config.ResourceLocator.*;

/**
 * Created by ford.arnett on 5/16/24
 */
public class NavOpsLogIn implements TestInitializerListener {
    AutomationTestManager am;

    @Override
    public void init(AutomationTestManager am) {
        this.am = am;
    }

    public void clickLogin() {
        am.driverWrapper.getElement(SAUCE_LOGIN_LOGIN_BUTTON).click();
    }

    public void enterUsername(String username) {
        am.driverWrapper.getElement(SAUCE_LOGIN_USERNAME.getBy()).sendKeys(username);
    }

    public void enterPasswords(String standardPassword) {
        am.driverWrapper.getElement(SAUCE_LOGIN_PASSWORD.getBy()).sendKeys(standardPassword);
    }
}
