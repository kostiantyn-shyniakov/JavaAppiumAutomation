package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            OVERFLOW_MENU = "//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']",
            HOME_MENU_OPTION = "//*[@resource-id='org.wikipedia:id/overflow_feed']",
            MY_LISTS = "//*[@content-desc='My lists']",
            SAVED_LIST = "//*[@text='Saved']";

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void goHome() {
        waitForElementAndClick(By.xpath(OVERFLOW_MENU));
        waitForElementAndClick(By.xpath(HOME_MENU_OPTION));
    }

    public void goToSavedList() {
        waitForElementAndClick(By.xpath(MY_LISTS));
        waitForElementAndClick(By.xpath(SAVED_LIST));
    }
}
