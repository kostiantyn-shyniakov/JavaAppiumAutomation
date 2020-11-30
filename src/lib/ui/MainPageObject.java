package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver=driver;
    }

    public boolean isElementPresent(String locator)
    {
        By by = getLocatorByString(locator);
        return driver.findElements(by).size()>0;
    }

    public void waitForElementAndSendKeys(String locator, String text)
    {
        WebElement element = waitForElementPresence(locator,"Element for sending keys is not found");
        element.clear();
        element.sendKeys(text);
    }

    public void waitForElementAndClick(String locator)
    {
        WebElement element = waitForElementPresence(locator,"Element for clicking is not found");
        element.click();
    }

    private WebElement waitForElementPresence(String locator, String error_massage, long timeoutInSeconds)
    {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresence(String locator, String error_massage)
    {
        return waitForElementPresence(
                locator,
                error_massage,
                5
        );
    }

    public boolean waitForElementNotPresent(String locator, String error_massage, long timeoutInSeconds)
    {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    protected By getLocatorByString(String locator_with_type){
        String[] exploaded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploaded_locator[0];
        String locator = exploaded_locator[1];
        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if (by_type.equals("id")){
            return By.id(locator);
        } else if (by_type.equals("css")){
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator:" + locator_with_type);
        }
    }
}
