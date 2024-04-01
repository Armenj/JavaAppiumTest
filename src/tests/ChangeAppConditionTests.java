package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = articlePageObject.getArticleTitle("Java (programming language)");
        this.rotatedScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle("Java (programming language)");
        assertEquals("Article title have been changed after screen rotation", title_before_rotation, title_after_rotation);
        this.rotatedScreenPortrait();
        String title_after_second_rotation = articlePageObject.getArticleTitle("Java (programming language)");
        assertEquals("Article title have been changed after screen rotation", title_before_rotation, title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        this.backgroundApp(2);
        searchPageObject.clickByArticleWithSubstring("Appium");
    }
}