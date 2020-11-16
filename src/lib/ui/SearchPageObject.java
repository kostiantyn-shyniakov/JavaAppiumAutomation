package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPageObject extends MainPageObject{

    private static final String
    SEARCH_INIT_ELEMENT = "//*[@text='Search Wikipedia']",
    SEARCH_LIST_ITEM = "org.wikipedia:id/page_list_item_title",
    CLOSE_BTN = "org.wikipedia:id/search_close_btn",
    ARTICLE_TITLE = "//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    public void typeSearchLine(String text)
    {
        waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT));
        waitForElementAndSendKeys(By.xpath(SEARCH_INIT_ELEMENT),text);
    }

    public WebElement checkSearchResult() {
        return waitForElementPresence(By.id(SEARCH_LIST_ITEM),"Search result is absent");
    }

    public boolean checkSearchResultIsEmpty() {
        return waitForElementNotPresent(By.id(SEARCH_LIST_ITEM),"Search result is NOT empty",5);
    }

    public void clickSearchCloseBtn() {
        waitForElementAndClick(By.id(CLOSE_BTN));
    }

    public boolean checkSearchResultContainsWord(String text, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(SEARCH_LIST_ITEM)));
        for(WebElement element:list){
            if (element.getText().contains(text)) return true;
        }
        return false;
    }

    public void openArticle() {
        waitForElementAndClick(By.id(SEARCH_LIST_ITEM));
    }

    public boolean checkArticleHasTitle() {
        return isElementPresent(By.xpath(ARTICLE_TITLE));
    }
}
