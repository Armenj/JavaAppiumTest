package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
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
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveTwoArticlesAndRemoveOne() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement("Object-oriented programming language");

        if (Platform.getInstance().isAndroid()) {
            String name_of_folder = "Learning programming";
            articlePageObject.addArticleToMyList(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            articlePageObject.addArticlesToSaved();
        }

        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        articlePageObject.waitForTitleElement("Appium");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addAnotherArticleToMyList();
        } else if (Platform.getInstance().isIOS()) {
            articlePageObject.addArticlesToSaved();
        }

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName("Learning programming");
        } else if (Platform.getInstance().isIOS()) {
            myListsPageObject.openSavedArticles();
        }
        myListsPageObject.swipeByArticleToDelete("Appium");

        // Проверяем, что после удаления осталась только одна статья
        int amountOfArticles = articlePageObject.getAmountOfArticles();
        assertEquals(
                "We supposed to find only one saved article after deletion",
                1,
                amountOfArticles
        );

        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String article_title = articlePageObject.getArticleTitle("Java (programming language)");
        System.out.println(article_title);
        assertEquals(
                "we see unexpected tittle",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testArticleHasTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertArticleTitlePresent();
    }
}