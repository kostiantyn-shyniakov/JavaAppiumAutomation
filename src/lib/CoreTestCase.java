package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";
    @Override
    protected void setUp() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals("iOS")){
            capabilities.setCapability("platformName","iOS");
            capabilities.setCapability("deviceName","iPhone SE");
            capabilities.setCapability("platformVersion","13.4");
            capabilities.setCapability("app","/Users/webteam/Desktop/Wikipedia.app");
            driver = new IOSDriver(new URL(AppiumURL), capabilities);
            TouchAction touchAction = new TouchAction(driver);
            touchAction.tap(69, 749).perform();
        } else if (platform.equals("android")){
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidDeviceName");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("orientation","PORTRAIT");
            capabilities.setCapability("app","C:\\Users\\kshyniakov\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);
            TouchAction touchAction = new TouchAction(driver);
            touchAction.tap(117, 1710).perform();
        } else {
            throw new Exception("Cannot get run platform from env veriable");
        }
        //clickSkip();
    }
    @Override
    protected void tearDown()
    {
        driver.quit();
    }

    private void clickSkip(){

    }
}
