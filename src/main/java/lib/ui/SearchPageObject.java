package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    SEARCH_LIST_ITEM,
    SEARCH_CANCEL_BTN,
    ARTICLE_TITLE;

    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void typeSearchLine(String text)
    {
        waitForElementAndClick(SEARCH_INIT_ELEMENT);
        waitForElementAndSendKeys(SEARCH_INPUT,text);
    }

    public WebElement checkSearchResult() {
        return waitForElementPresence(SEARCH_LIST_ITEM,"Search result is absent");
    }

    public boolean checkSearchResultIsEmpty() {
        return waitForElementNotPresent(SEARCH_LIST_ITEM,"Search result is NOT empty",5);
    }

    public void clickSearchCloseBtn() {
        waitForElementAndClick(SEARCH_CANCEL_BTN);
    }

    public boolean checkSearchResultContainsWord(String text, RemoteWebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocatorByString(SEARCH_LIST_ITEM)));
        for(WebElement element:list){
            if (element.getText().contains(text)) return true;
        }
        return false;
    }

    public void openArticle() {
        waitForElementAndClick(SEARCH_LIST_ITEM);
    }

    public boolean checkArticleHasTitle() {
        return isElementPresent(ARTICLE_TITLE);
    }
}
