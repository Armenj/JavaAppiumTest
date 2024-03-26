package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

abstract public class SearchPageObject extends MainPageObject {
     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SKIP_BUTTON,
            GET_SEARCH_RESULT_LOCATOR,
            EMPTY_SEARCH_RESULT,
            PAGE_LIST_APPIUM;


    private static String getResultSearchElement(String substring) {
        return "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + substring + "']";
    }

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find skip button", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with text: " + substring, 5);
    }

    public void clickSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with text: " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void thereIsNotResultOfSearch(){
        this.waitForElementNotPresent(EMPTY_SEARCH_RESULT, "Empty search result",5);
    }

    public int getSearchResultCount(){
        this.waitForElementPresent(GET_SEARCH_RESULT_LOCATOR, "Cannot find anithing by the request", 5);
        return this.getAmountOfElements(GET_SEARCH_RESULT_LOCATOR);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present!", 5);
    }

    public int getAmountOfSearchResults() {
        this.waitForElementPresent(GET_SEARCH_RESULT_LOCATOR, "Cannot find anything by the request", 5);
        return this.getAmountOfElements(GET_SEARCH_RESULT_LOCATOR);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent((EMPTY_SEARCH_RESULT), "Some results were found by the request.");
    }

    public void verifySearchResultsContainText(String expectedText) {
        List<WebElement> searchResults = driver.findElements(getLocatorByString(PAGE_LIST_APPIUM));
        int numberOfResultsToCheck = searchResults.size();

        for (int i = 0; i < numberOfResultsToCheck; i++) {
            String resultText = searchResults.get(i).getText();
            assertTrue("Search result does not contain expected text: " + expectedText, resultText.contains(expectedText));
        }
    }
}