package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPaggeObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    private static final String

     searchInput = "xpath://*[@text='Search Wikipedia']",
     goBackArrow = "xpath://*[@content-desc='Navigate up']",
     pageListAppium = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.thereIsNotResultOfSearch();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Linkin Park Diskography");
        int getSearchResultCount = searchPageObject.getSearchResultCount();
        System.out.println("Amount of search results: " + getSearchResultCount);
        assertTrue("We found too few results", getSearchResultCount > 0);
    }

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchInputText() {
        MainPageObject mainPageObject = new MainPageObject(driver);
//        mainPageObject.skipOnboarding();
        mainPageObject.assertElementHasText(searchInput, "Search Wikipedia", "Search input does not contain the expected text.");
    }

    @Test
    public void testSearchResultsMoreThanOne() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitForNumberOfElementsToBeMoreThan(pageListAppium, 1, "Not enough articles found after search", 5);
        mainPageObject.waitForElementAndClick(goBackArrow, "Cannot find arrow");
        mainPageObject.waitForElementNotPresent(goBackArrow, "Go back arrow is still presetn on the page", 10);
    }

    @Test
    public void testSearchResultsContainsJava() {
        SearchPageObject searchPageObject = SearchPaggeObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.verifySearchResultsContainText("Java");
    }

}