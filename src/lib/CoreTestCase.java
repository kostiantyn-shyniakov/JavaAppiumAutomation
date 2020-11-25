package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import junit.framework.TestCase;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        driver = Platform.getInstance().getDriver();
        TouchAction touchAction = new TouchAction(driver);

        if (Platform.getInstance().isAndroid())
            touchAction.tap(117, 1710).perform();
        else
            touchAction.tap(69, 749).perform();
    }
    @Override
    protected void tearDown()
    {
        driver.quit();
    }
}
