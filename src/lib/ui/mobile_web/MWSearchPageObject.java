package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_LIST_ITEM = "css:form>input[type='search']";
        CLOSE_BTN = "css:div.header-action button.cancel";
        ARTICLE_TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
