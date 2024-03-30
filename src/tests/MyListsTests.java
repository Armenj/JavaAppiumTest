package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactories;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPaggeObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.skip();
//        searchPageObject.initSearchInput();
//        searchPageObject.typeSearchLine("Appium");
//        searchPageObject.clickByArticleWithSubstring("Appium");
//        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
//        articlePageObject.waitForTitleElement("Appium");
//        String name_of_folder = "Learning programming";
//
//        if(Platform.getInstance().isAndroid()){
//            articlePageObject.addArticleToMyList(name_of_folder);
//        }else {
//            articlePageObject.addArticleToMySaved();
//        }
//        articlePageObject.addArticleToMyList(name_of_folder);
//        NavigationUI navigationUI = NavigationUIFactory.get(driver);
//        navigationUI.closeArticle();
//        navigationUI.closeArticle();
//        navigationUI.clickOnTheSavedButton();
//        MyListsPageObject myListsPageObject = MyListsPageObjectFactories.get(driver);
//        if(Platform.getInstance().isAndroid()){
//            myListsPageObject.openFolderByName("Test");
//        }
//        myListsPageObject.swipeByArticleToDelete("Appium");
    }
}
