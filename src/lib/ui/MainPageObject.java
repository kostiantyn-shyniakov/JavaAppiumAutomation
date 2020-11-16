package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver=driver;
    }

    public boolean isElementPresent(By by)
    {
        return driver.findElements(by).size()>0;
    }

    public void waitForElementAndSendKeys(By by, String text)
    {
        WebElement element = waitForElementPresence(by,"Element for sending keys is not found");
        element.clear();
        element.sendKeys(text);
    }

    public void waitForElementAndClick(By by)
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

    public WebElement waitForElementPresence(By by, String error_massage)
    {
        return waitForElementPresence(
                by,
                error_massage,
                5
        );
    }

    public boolean waitForElementNotPresent(By by, String error_massage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void assertElementHasText(By by, String expected_text, String error_massage){
        WebElement element = waitForElementPresence(by, "Cannot find TextView element in search container");
        Assert.assertEquals(error_massage,element.getText(),expected_text);
    }
}
