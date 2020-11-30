package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
        SEARCH_LIST_ITEM = "id:org.wikipedia:id/page_list_item_title";
        CLOSE_BTN = "id:org.wikipedia:id/search_close_btn";
        ARTICLE_TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
