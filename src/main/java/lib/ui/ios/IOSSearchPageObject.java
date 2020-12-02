package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "";
        SEARCH_LIST_ITEM = "";
        SEARCH_CANCEL_BTN = "";
        ARTICLE_TITLE = "";
    }

    public IOSSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
