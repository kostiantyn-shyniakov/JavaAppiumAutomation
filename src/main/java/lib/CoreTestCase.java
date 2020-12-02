package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import junit.framework.TestCase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        driver = Platform.getInstance().getDriver();

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction touchAction = new TouchAction(driver);

            if (Platform.getInstance().isAndroid())
                touchAction.tap(117, 1710).perform();
            else
                touchAction.tap(69, 749).perform();
        }

        if(Platform.getInstance().isMobileWeb()){
            driver.manage().window().setSize(new Dimension(340,640));
            driver.get("https://en.m.wikipedia.org");
        }
    }
    @Override
    protected void tearDown()
    {
        driver.quit();
    }
}
