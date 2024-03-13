package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class SearchPageObject extends MainPageObject {
    private static final By
            SEARCH_INIT_ELEMENT = By.xpath("//*[@text='Search Wikipedia']"),
            SEARCH_INPUT = By.id("org.wikipedia:id/search_src_text"),
            SEARCH_CANCEL_BATTON = By.id("org.wikipedia:id/search_close_btn"),
            SKIP_BUTTON = By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
            GET_SEARCH_RESULT_LOCATOR = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup"),
            EMPTY_SEARCH_RESULT = By.xpath("//*[@text='No results']"),
            PAGE_LIST_APPIUM = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']");

    private static String getResultSearchElement(String substring){
        // Здесь мы динамически подставляем значение подстроки в XPath
        return "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='"+ substring + "']";
    }

    public SearchPageObject(AndroidDriver driver){
        super(driver);
    }

    public void initSearchInput(){
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find skip button", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void clickSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with text: " + substring, 5);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        System.out.println("Generated XPath: " + search_result_xpath);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with text: " + substring, 10);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BATTON, "Cannot find search cancel button", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BATTON, "Cannot find search cancel button");
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BATTON, "Search cancel button is still present!", 5);
    }

    public int getSearchResultCount(){
        this.waitForElementPresent(GET_SEARCH_RESULT_LOCATOR, "Cannot find anithing by the request", 5);
        return this.getAmountOfElements(GET_SEARCH_RESULT_LOCATOR);
    }

    public void thereIsNotResultOfSearch(){
        this.waitForElementNotPresent(EMPTY_SEARCH_RESULT, "Empty search result",5);
    }

    public void verifySearchResultsContainText(String expectedText) {
        List<WebElement> searchResults = driver.findElements(PAGE_LIST_APPIUM);
        int numberOfResultsToCheck = searchResults.size();

        for (int i = 0; i < numberOfResultsToCheck; i++) {
            String resultText = searchResults.get(i).getText();
            assertTrue("Search result does not contain expected text: Java",
                    resultText.contains("Java"));
        }
    }
}