package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "";
        SEARCH_LIST_ITEM = "";
        CLOSE_BTN = "";
        ARTICLE_TITLE = "";
    }

    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
