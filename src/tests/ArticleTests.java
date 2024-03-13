package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
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
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveTwoArticlesAndRemoveOne() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");//от начала и до открытия статьи
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement("Java (programming language)");
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder); //сохраняет и дает название
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        articlePageObject.waitForTitleElement("Appium");
        articlePageObject.addAnotherArticleToMyList();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.selectSavedFolderAndReturnHome(name_of_folder);
        myListsPageObject.swipeByArticleToDelete("Appium");
        articlePageObject.getArticleTitleAndValidate("Java (programming language)");
    }

    @Test
    public void testArticleHasTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertArticleTitlePresent();
    }
}