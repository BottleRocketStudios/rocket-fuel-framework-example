package operations;

import automationtestinstance.*;
import automationtests.assertions.*;
import com.bottlerocket.config.*;
import com.bottlerocket.utils.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


public class Steps {
    AutomationTestManager am;
    public Steps(AutomationTestManager am) {
        this.am = am;
    }

    public void I_click_on_Button___(ResourceLocatorBundle button) {
        String step = "I click on Button " + button.getBy().toString();
        am.stepList.add(step);
        am.driverWrapper.getElement(button).click();
    }

    public void I_click_the___key_on_element___(String key, ResourceLocatorBundle element) {
        String step = "I click the " + key +  " key on element " + element.getBy().toString();
        am.stepList.add(step);
        am.driverWrapper.getElement(element).sendKeys(Keys.valueOf(key.toUpperCase()));
    }

    public void I_navigate_to_Url___(String url) {
        String step = "I navigate to Url " + url;
        am.stepList.add(step);
        am.driverWrapper.navigateTo(url);
    }

    public void IAssertA___EqualsB___ (String A, String B ) {
        String step = "I assert A: " + A + " equals B: " + B;
        am.stepList.add(step);
        am.assertions.generalAssertion(new AssertionPayload(A.equals(B), "The values are Equal", "The values are not equal"));

    }

    public void I_enter_text___to_textField___(String text, ResourceLocatorBundle textField) {
        String step = "I enter text: " + text + " to textField: " + textField.getBy().toString();
        am.stepList.add(step);
        am.driverWrapper.getElement(textField).sendKeys(text);
    }

    public void I_assert_Url_contains_text___(String text) {
        String step = "I assert Url contains text: " + text;
        am.stepList.add(step);
        String url = am.driverWrapper.getCurrentURL().toString();
        am.assertions.generalAssertion(new AssertionPayload(url.contains(text), "Url contains text: " + text, "Url does NOT contain text: " + text + "Curent Url is:  " + url));
//        String screenShot = am.driverWrapper.ta
    }

    public void I_wait_for_some_seconds(int waitTime) {
        String step = "I wait for some seconds: " + (waitTime * 1000);
        am.stepList.add(step);
        am.driverWrapper.waitLogErr(waitTime * 1000);
    }

    public void I_wait_for_element___to_be_displayed(ResourceLocatorBundle element) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        String step = "I wait for element: " + element.getBy().toString() + " to be displayed";
        am.stepList.add(step);
        am.driverWrapper.driverWait.until(ExpectedConditions.visibilityOfElementLocated(getResourceLocatorBundleBy(element)));
    }

    public void I_scroll_to_element(ResourceLocatorBundle element) {
        String step = "I scroll to element: " + element.getBy().toString();
        am.stepList.add(step);
        WebElement targetElement = am.driverWrapper.getElement(element);
        ((JavascriptExecutor) am.driverWrapper).executeScript("arguments[0].scrollIntoView(true);", targetElement);
    }

    public void I_select_option_from_dropdown(String optionText, ResourceLocatorBundle dropdown) {
        String step = "I select option '" + optionText + "' from dropdown: " + dropdown.getBy().toString();
        am.stepList.add(step);
        Select dropdownSelect = new Select(am.driverWrapper.getElement(dropdown));
        dropdownSelect.selectByVisibleText(optionText);
    }

//    public void I_tap_the_screen_location_of_element___(ResourceLocatorBundle element) {
//        String step = "I tap the screen location of element: " + element.getBy().toString();
//        am.stepList.add(step);
//        am.userOp.tapElementByLocation(element);
//    }






    public void printSteps() {
        for ( String step : am.stepList ) {
            Logger.log(step + "\n");
        }
    }

    private By getResourceLocatorBundleBy(ResourceLocatorBundle rlb) {
        return rlb.getBy();
    }

}
