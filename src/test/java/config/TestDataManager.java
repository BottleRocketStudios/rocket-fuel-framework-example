package config;

import com.bottlerocket.errorhandling.ConfigurationException;
import com.bottlerocket.utils.Logger;
import domod.User;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Treat this class as more of an idea than a hard and fast rule. So what is that idea? Well depending on your project this could be many things.
 * <p>
 * The one thing this *should* do is be the gatekeeper of the data you need to run a particular test case. Use this to streamline test
 * data management so that you are not having to mess with it across a lot of your test cases. You can put different types of accounts, various attributes, datasets, etc. in here!
 * <p>
 * DO: pass in environment, locale, test type, etc. Use this to pick the data that makes the most sense for your run
 * DONT: have one user across seven test cases, a different user in a separate test case, etc. You may need a one off user in a method, and that's fine, that's not what this is about. Instead this is
 * an easy way to keep that user you need across the seven test cases and have a single spot where you can modify it, and where it can change depending on whether you are using staging/qa/prod.
 * DO: Use as much as little in here as you need
 * DONT: Be afraid to delete all this test code. It's just an example
 * DO: Call this before you start your testing. Pass it the parameters it needs, then let it set up your data
 * DONT: Forget to share your improvements on this paradigm in code review!
 * DO: Feel free to pair this with a data provider, excel loader, or any other data source!
 * <p>
 * <p>
 * Created by ford.arnett on 10/6/21
 */
public class TestDataManager {
    public User activeUser;

    protected User qaUser = new User("standard_user", "secret_sauce");
    protected User prodUser = new User("User", "Password");


    public final static String LOCALE_US = "US";
    public final static String LOCALE_CANADA = "CA";

    public final static String QA = "qa";
    public final static String STAGING = "staging";
    public final static String PROD = "prod";

    final static String CURRENT_ENV = QA;


    private String currentEnv;
    private String currentLocale;

    public TestDataManager(String env, String locale) {
        currentLocale = locale;
        currentEnv = env;

        setUser(currentEnv, locale);
        setLocaleData(currentLocale);
    }


    public String getCurrentLocale() {
        return currentLocale;
    }

    public String getCurrentEnv() {
        return currentEnv;
    }

    public void setUser(String env, String locale) {
        if (env.equals(PROD)) {
            activeUser = prodUser;
        } else if (env.equals(QA)) {
            activeUser = qaUser;
        }

        Logger.log("Active user is set to " + activeUser);

    }

    public void setLocaleData(String currentLocale) {
        if (currentLocale.equals(LOCALE_US)) {
            //set US params
        } else if (currentLocale.equals(LOCALE_CANADA)) {
            //set CANADA, eh?
        } else {
            Logger.log("Locale not recognized");
        }
    }

    public void setEnvCapabilities(DesiredCapabilities capabilities, String env, String locale) {
        if (env.equals(PROD)) {
            setNormalEnv(capabilities, locale);
        } else if (env.equals(QA)) {
            //setQA(capabilities, locale);
        }
    }

    private void setNormalEnv(DesiredCapabilities capabilities, String locale) {
        final String envCapability = "{ \"PROJ_ENV_IDENTIFIER\": \"QA\", \"URL\": \"url to pass in\"";

        //This is something nifty we did in the past. This lets us pass a variable into iOS so that the dev can use it. For example passing in an variable to tell what environment to launch in. Requires dev support!
        capabilities.setCapability("processArguments", envCapability);
        Logger.log("Setting processArguments to " + envCapability);

        //Can also set language/locale based on params. Note these are different for iOS/Android.
        //This can also be done through properties file but make sure your version of the framework supports it!
        capabilities.setCapability("language", "en");
        if (locale.equals(LOCALE_US)) {
            capabilities.setCapability("locale", "en_US");
        } else if (locale.equals(LOCALE_CANADA)) {
            capabilities.setCapability("locale", "en_CA");
        }
    }


}