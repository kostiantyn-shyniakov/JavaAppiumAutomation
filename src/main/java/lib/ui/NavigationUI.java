package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject {

    private static final String
            OVERFLOW_MENU = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']",
            HOME_MENU_OPTION = "xpath://*[@resource-id='org.wikipedia:id/overflow_feed']",
            MY_LISTS = "xpath://*[@content-desc='My lists']",
            SAVED_LIST = "xpath://*[@text='Saved']";

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    public void goHome() {
        waitForElementAndClick(OVERFLOW_MENU);
        waitForElementAndClick(HOME_MENU_OPTION);
    }

    public void goToSavedList() {
        waitForElementAndClick(MY_LISTS);
        waitForElementAndClick(SAVED_LIST);
    }
}
