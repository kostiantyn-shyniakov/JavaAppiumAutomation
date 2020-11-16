import lib.CoreTestCase;
import lib.ui.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageProject;
    private SearchPageObject SearchPageObject;
    private ArticlePageObject ArticlePageObject;
    private NavigationUI NavigationUI;
    private MyListsPageObject MyListsPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageProject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI = new NavigationUI(driver);
        MyListsPageObject = new MyListsPageObject(driver);
    }

    public void testSearchAndCancel()
    {
        SearchPageObject.typeSearchLine("Automation");
        assertTrue(SearchPageObject.checkSearchResult().isDisplayed());
        SearchPageObject.clickSearchCloseBtn();
        assertTrue(SearchPageObject.checkSearchResultIsEmpty());
    }

    public void testSearchResultContent()
    {
        SearchPageObject.typeSearchLine("Java");
        assertTrue(SearchPageObject.checkSearchResultContainsWord("Java", driver));
    }

    public void testTitlePresent()
    {
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.openArticle();
        assertTrue("Title is not found", SearchPageObject.checkArticleHasTitle());
    }

    public void testPresenceOfArticleAfterDeletingAnotherOne()
    {
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.openArticle();
        String title1 = ArticlePageObject.saveArticelAndGetTitle();
        NavigationUI.goHome();
        SearchPageObject.typeSearchLine("Automation");
        SearchPageObject.openArticle();
        String title2 = ArticlePageObject.saveArticelAndGetTitle();
        NavigationUI.goHome();
        NavigationUI.goToSavedList();
        MyListsPageObject.deleteItemFromSavedListAndGoHome(title2);
        NavigationUI.goHome();
        NavigationUI.goToSavedList();
        assertTrue("Searching title is absent", MyListsPageObject.checkTitleInSavedList(title1));
    }
}
