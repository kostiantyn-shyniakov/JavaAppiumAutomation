package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_LIST_ITEM = "css:ul>li.page-summary";
        SEARCH_CANCEL_BTN = "css:div.header-action button.cancel";
        ARTICLE_TITLE = "";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
