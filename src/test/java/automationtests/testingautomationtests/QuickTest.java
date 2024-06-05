package automationtests.testingautomationtests;
/*
  Created by ford.arnett on 4/1/16.
 */

import testmain.TestMain;
import automationtests.assertions.AssertionPayload;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class QuickTest extends TestMain {

    @BeforeClass
    public void setup() {

    }

    @Test
    public void quickTest(){
        //assertionLogger.addMessage("This is a quick and pointless test, used to test the automation suite is working as intended");
        //assertionLogger.setTestType("Pointless test");
        //assertionLogger.assertEquals("42", "42");
        System.out.println("something");
        String myString = "hello";

    }

    @Test
    public void quickTestAssertionsFail(){
        am.assertions.generalAssertion(new AssertionPayload(true, "This is a test using the frameworks general assertions to verify behavior.", ""));
        am.assertions.generalAssertion(new AssertionPayload(false, "", "This is a test using the frameworks general assertions to verify behavior, testing fail"));
        am.assertions.generalAssertion(new AssertionPayload(true, "This is a test using the frameworks general assertions to verify behavior.", ""));
    }

    @Test
    public void quickTestAssertionsPass(){
        am.assertions.generalAssertion(new AssertionPayload(true, "This is a test using the frameworks general assertions to verify behavior.", ""));
        am.assertions.generalAssertion(new AssertionPayload(true, "This is a test using the frameworks general assertions to verify behavior.", ""));
        am.assertions.generalAssertion(new AssertionPayload(true, "This is a test using the frameworks general assertions to verify behavior.", ""));


    }

    @Test
    public void testUnexpectedFail() throws Exception {
        throw new Exception("This represents a fail we didn't expect");
    }


    @AfterClass
    public void tearDown() {
    }
}