package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject{

    private static final String
            ARTICLE_TITLE = "//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View",
            ARTICLE_MENU_BOOKMARK = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public String saveArticelAndGetTitle() {
        waitForElementAndClick(By.xpath(ARTICLE_MENU_BOOKMARK));
        String title=waitForElementPresence(By.xpath(ARTICLE_TITLE),"Can't find title").getText();
        return title;
    }
}
