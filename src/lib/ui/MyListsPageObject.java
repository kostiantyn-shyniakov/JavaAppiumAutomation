package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject{

    private static final String
            SAVE_BTN = "xpath://*[@text='Save']",
            REMOVED_FROM_SAVED = "xpath://*[@text='Remove from Saved']";

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    public boolean checkTitleInSavedList(String title) {
        return waitForElementPresence("xpath://*[@text='"+title+"']","WebElement with specified title is absent").isDisplayed();
    }

    public void deleteItemFromSavedListAndGoHome(String title) {
        waitForElementAndClick("xpath://*[@text='"+title+"']");
        waitForElementAndClick(SAVE_BTN);
        waitForElementAndClick(REMOVED_FROM_SAVED);
    }
}
