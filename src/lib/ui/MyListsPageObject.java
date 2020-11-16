package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    public boolean checkTitleInSavedList(String title) {
        return waitForElementPresence(By.xpath("//*[@text='"+title+"']"),"WebElement with specified title is absent").isDisplayed();
    }

    public void deleteItemFromSavedListAndGoHome(String title) {
        waitForElementAndClick(By.xpath("//*[@text='"+title+"']"));
        waitForElementAndClick(By.xpath("//*[@text='Save']"));
        waitForElementAndClick(By.xpath("//*[@text='Remove from Saved']"));
    }
}
