package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement("Appium");
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder);
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.closeArticle();
        navigationUI.closeArticle();
        navigationUI.clickOnTheSavedButton();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName("Test");
        myListsPageObject.swipeByArticleToDelete("Appium");
    }
}
