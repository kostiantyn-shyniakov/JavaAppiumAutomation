package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject{

    private static final String
            ARTICLE_TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View",
            ARTICLE_MENU_BOOKMARK = "xpath://*[@resource-id='org.wikipedia:id/article_menu_bookmark']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public String saveArticelAndGetTitle() {
        waitForElementAndClick(ARTICLE_MENU_BOOKMARK);
        String title=waitForElementPresence(ARTICLE_TITLE,"Can't find title").getText();
        return title;
    }
}
