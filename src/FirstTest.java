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
import java.util.List;

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
        clickSkip();
        By search_container = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class='android.widget.TextView']");
        assertElementHasText(search_container,"Search Wikipedia","Expected text is absent in search container");
    }

    @Test
    public void checkSearchAndCancel()
    {
        sendKeysToSearchToolBar("Automation");
        Assert.assertTrue(waitForElementPresence(By.id("org.wikipedia:id/page_list_item_title"), "Search result is absent").isDisplayed());
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"));
        Assert.assertTrue(waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"), "Search result is present",5));
    }

    @Test
    public void checkSearchResultContent()
    {
        sendKeysToSearchToolBar("Java");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("org.wikipedia:id/page_list_item_title")));
        for(WebElement element:list){
            Assert.assertTrue("One of search result items doesn't contain the searching word", element.getText().contains("Java"));
        }
    }

    @Test
    public void assertTitlePresent()
    {
        sendKeysToSearchToolBar("Java");
        waitForElementAndClick(By.id("org.wikipedia:id/page_list_item_title"));
        Assert.assertTrue("Title is not found", isElementPresent(By.xpath("//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View")));
    }

    private boolean isElementPresent(By by)
    {
        return driver.findElements(by).size()>0;
    }

    private void sendKeysToSearchToolBar(String text)
    {
        clickSkip();
        waitForElementAndClick(By.xpath("//*[@text='Search Wikipedia']"));
        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"),text);
    }

    private void waitForElementAndSendKeys(By by, String text)
    {
        WebElement element = waitForElementPresence(by,"Element is not found");
        element.sendKeys(text);
    }

    private void waitForElementAndClick(By by)
    {
        WebElement element = waitForElementPresence(by,"Element is not found");
        element.click();
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

    private boolean waitForElementNotPresent(By by, String error_massage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private void assertElementHasText(By by, String expected_text, String error_massage){
        WebElement element = waitForElementPresence(by, "Cannot find TextView element in search container");
        Assert.assertEquals(error_massage,element.getText(),expected_text);
    }

    private void clickSkip(){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(117, 1710).perform();
    }
}
