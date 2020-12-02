package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "";
        ARTICLE_MENU_BOOKMARK = "";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
