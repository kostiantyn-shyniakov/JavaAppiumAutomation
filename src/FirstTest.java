import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidDeviceName");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\kshyniakov\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void checkTextInSearchContainer()
    {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(117, 1710).perform();       //tap 'Skip'
        By search_container = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class='android.widget.TextView']");
        assertElementHasText(search_container,"Search Wikipedia","Expected text is absent in search container");
    }

    private WebElement waitForElementPresence(By by, String error_massage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresence(By by, String error_massage)
    {
        return waitForElementPresence(
                by,
                error_massage,
                5
        );
    }

    private void assertElementHasText(By by, String expected_text, String error_massage){
        WebElement element = waitForElementPresence(by, "Cannot find TextView element in search container");
        Assert.assertEquals(error_massage,element.getText(),expected_text);
    }
}
