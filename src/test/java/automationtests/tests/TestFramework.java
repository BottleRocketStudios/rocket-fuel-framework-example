package automationtests.tests;


import com.bottlerocket.config.ResourceLocatorBundle;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testmain.TestMain;

import java.io.IOException;

import static config.ResourceLocator.*;

public class TestFramework extends TestMain {
String locator = "xpath://*[@text='Test']";
    @BeforeClass
    public void setup() throws IOException {

    }

    @Test
    public void testFramework() {

    }

    @AfterClass
    public void tearDown() {

    }
}
