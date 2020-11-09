import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
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
        capabilities.setCapability("orientation","PORTRAIT");
        capabilities.setCapability("app","C:\\Users\\kshyniakov\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        clickSkip();
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void checkTextInSearchContainer()
    {
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
        openArticle("Java");
        Assert.assertTrue("Title is not found", isElementPresent(By.xpath("//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View")));
    }

    @Test
    public void checkPresenceOfArticleAfterDeletingAnotherOne()
    {
        String title1 = saveArticelAndGoHome("Java");
        String title2 = saveArticelAndGoHome("Automation");
        goToSavedList();
        deleteItemFromSavedListAndGoHome(title2);
        goToSavedList();
        Assert.assertTrue("Searching title is absent", checkPresenceOfTitleInSavedList(title1));
    }

    private boolean checkPresenceOfTitleInSavedList(String title) {
        return waitForElementPresence(By.xpath("//*[@text='"+title+"']"),"WebElement with specified title is absent").isDisplayed();
    }

    private void goToSavedList() {
        waitForElementAndClick(By.xpath("//*[@content-desc='My lists']"));
        waitForElementAndClick(By.xpath("//*[@text='Saved']"));
    }

    private void deleteItemFromSavedListAndGoHome(String title) {
        waitForElementAndClick(By.xpath("//*[@text='"+title+"']"));
        waitForElementAndClick(By.xpath("//*[@text='Save']"));
        waitForElementAndClick(By.xpath("//*[@text='Remove from Saved']"));
        goHome();
    }

    private String saveArticelAndGoHome(String text) {
        openArticle(text);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"));
        String title=waitForElementPresence(By.xpath("//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View"),"Can't find title").getText();
        goHome();
        return title;
    }

    private void goHome() {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"));
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/overflow_feed']"));
    }

    private void openArticle(String text) {
        sendKeysToSearchToolBar(text);
        waitForElementAndClick(By.id("org.wikipedia:id/page_list_item_title"));
    }

    private boolean isElementPresent(By by)
    {
        return driver.findElements(by).size()>0;
    }

    private void sendKeysToSearchToolBar(String text)
    {
        waitForElementAndClick(By.xpath("//*[@text='Search Wikipedia']"));
        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"),text);
    }

    private void waitForElementAndSendKeys(By by, String text)
    {
        WebElement element = waitForElementPresence(by,"Element for sending keys is not found");
        element.clear();
        element.sendKeys(text);
    }

    private void waitForElementAndClick(By by)
    {
        WebElement element = waitForElementPresence(by,"Element for clicking is not found");
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
