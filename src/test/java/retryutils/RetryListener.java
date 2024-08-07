package retryutils;

import org.testng.*;
import org.testng.annotations.*;

import java.lang.reflect.*;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation testannotation, Class testClass,
                          Constructor testConstructor, Method testMethod)   {
        Class<? extends IRetryAnalyzer> retry = testannotation.getRetryAnalyzerClass();

        if (retry == null)  {
            testannotation.setRetryAnalyzer(RetryAnalyzer.class);
        }

    }
}