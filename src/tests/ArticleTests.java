package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactories;
import lib.ui.factories.SearchPaggeObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle("Java (programming language)");
        System.out.println(article_title);
        assertEquals(
                "we see unexpected tittle",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticle() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveTwoArticlesAndRemoveOne() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement("Java (programming language)");
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder);
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        articlePageObject.waitForTitleElement("Appium");
        articlePageObject.addAnotherArticleToMyList();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactories.get(driver);
        myListsPageObject.selectSavedFolderAndReturnHome(name_of_folder);
        myListsPageObject.swipeByArticleToDelete("Appium");
        articlePageObject.getArticleTitleAndValidate("Java (programming language)");
    }

    @Test
    public void testArticleHasTitle() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertArticleTitlePresent();
    }
}