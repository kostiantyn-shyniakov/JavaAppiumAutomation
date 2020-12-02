package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject{

    protected static String
            ARTICLE_TITLE,
            ARTICLE_MENU_BOOKMARK;

    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    public String saveArticelAndGetTitle() {
        waitForElementAndClick(ARTICLE_MENU_BOOKMARK);
        String title=waitForElementPresence(ARTICLE_TITLE,"Can't find title").getText();
        return title;
    }
}
