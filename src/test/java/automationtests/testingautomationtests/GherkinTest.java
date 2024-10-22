package automationtests.testingautomationtests;

import org.testng.annotations.*;
import testmain.*;

import static config.ResourceLocator.*;

public class GherkinTest extends TestMain {

    @Test(groups="demoTest")
    public void Test1() {
        testCaseId = "2931803";

        am.steps.I_navigate_to_Url___(URL_GOOGLE);

        am.steps.I_wait_for_element___to_be_displayed(INPUT_TEXT_BOX_GOOGLE_SEARCH);

        am.steps.I_enter_text___to_textField___("espn boxing schedule", INPUT_TEXT_BOX_GOOGLE_SEARCH);

        am.steps.I_click_the___key_on_element___("Enter", INPUT_TEXT_BOX_GOOGLE_SEARCH);

        am.steps.I_assert_Url_contains_text___("boxing+schedule");

        am.steps.printSteps();
    }

}
