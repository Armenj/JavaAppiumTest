import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.*;
import java.util.List;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    private By skipButton = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private By searchInput = By.xpath("//*[@text='Search Wikipedia']");
    private By toIntoSearch = By.id("org.wikipedia:id/search_src_text");
    private By javaArticleSearchResult = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']");
    private By getSearchResultLocator = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup");
    private By goBackArrow = By.xpath("//*[@content-desc='Navigate up']");
    private By javaArticleTitle = By.xpath("//*[@text='Java (programming language)']");
    private By javaText = By.id("org.wikipedia:id/search_src_text");
    private By pageListAppium = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']");
    private By pageFuter = By.xpath("//*[@text='View article in browser']");
    private By appiumArticleSearchResult = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']");
    private By moreOptionsButton = By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu");
    private By saveArticleButton = By.id("org.wikipedia:id/page_save");
    private By addToListButton = By.id("org.wikipedia:id/snackbar_action");
    private By nameOfTheListArea = By.id("org.wikipedia:id/text_input");
    private By okButtonOnThePopUp = By.id("android:id/button1");
    private By savedTabButton = By.xpath("(//*[@resource-id='org.wikipedia:id/navigation_bar_item_icon_view'])[2]");
    private By listOnTheSaved = By.xpath("(//*[@resource-id='org.wikipedia:id/item_title_container'])[2]");
    private By emptyResultLabel = By.xpath("//*[@text='No results']");
    private By javaTitle = By.xpath("//android.widget.TextView[@text='Java (programming language)']");
    private By chooseSaveFolderButton = By.id("org.wikipedia:id/item_title");

    @Test
    public void testSaveTwoArticlesAndRemoveOne(){
        MainPageObject.waitForElementAndClick(skipButton, "Cannot find skip button");
        MainPageObject.waitForElementAndClick(searchInput, "Cannot find search input");
        MainPageObject.waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        MainPageObject.waitForElementAndClick(javaArticleSearchResult, "Result not present");
        MainPageObject.waitForElementAndClick(saveArticleButton, "Cannot find more_options button");
        MainPageObject.waitForElementAndClick(addToListButton, "Cannot find add to list button");
        MainPageObject.waitForElementAndSendKeys(nameOfTheListArea, "Homework", "Cannot find area");
        MainPageObject.waitForElementAndClick(okButtonOnThePopUp, "Cannot find ok button on the pop-up");

        MainPageObject.waitForElementAndClick(searchInput, "Cannot find search input");
        MainPageObject.waitForElementAndSendKeys(toIntoSearch, "Appium", "Cannot find search result");
        MainPageObject.waitForElementAndClick(appiumArticleSearchResult, "Result not present");
        MainPageObject.waitForElementAndClick(saveArticleButton, "Cannot find more_options button");
        MainPageObject.waitForElementAndClick(addToListButton, "Cannot find add to list button");

        MainPageObject.waitForElementAndClick(chooseSaveFolderButton, "Cannot find the folder to save the article");

        for (int i = 0; i < 3; i++) {
            MainPageObject.waitForElementAndClick(goBackArrow, "Cannot find arrow");
        }
        MainPageObject.waitForElementAndClick(savedTabButton, "Cannot find saved button");
        MainPageObject.waitForElementAndClick(listOnTheSaved, "Cannot find list on the saved page");
        MainPageObject.swipeElementLeftToRight(pageListAppium, "Cannot find page list");

        MainPageObject.waitForElementPresent(javaArticleSearchResult, "Article search result not present");
        MainPageObject.waitForElementAndClick(javaArticleSearchResult, "Cannot click on article search result");

        WebElement titleElement = MainPageObject.waitForElementToBeVisible(javaArticleTitle, "Article title not found", 10);
        String title = titleElement.getAttribute("text");
        System.out.println(title);
        assertEquals(
                "we see unexpected tittle",
                "Java (programming language)",
                title
        );
    }

    @Test
    public void testArticleHasTitle(){
        MainPageObject.waitForElementAndClick(skipButton, "Cannot find skip button");
        MainPageObject.waitForElementAndClick(searchInput, "Cannot find search input");
        MainPageObject.waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        MainPageObject.waitForElementAndClick(javaArticleSearchResult, "Result not present");
        MainPageObject.assertElementPresent(javaArticleTitle, "Article title is not present on the page without waiting");
    }

    @Test
    public void testSearchInputText() {
        MainPageObject.waitForElementAndClick(skipButton, "Cannot find skip button");
        MainPageObject.assertElementHasText(searchInput, "Search Wikipedia", "Search input does not contain the expected text.");
    }

    @Test
    public void testSearchResultsMoreThanOne() {
        MainPageObject.waitForElementAndClick(skipButton, "Cannot find skip button");
        MainPageObject.waitForElementAndClick(searchInput, "Cannot find search input");
        MainPageObject.waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        MainPageObject.waitForNumberOfElementsToBeMoreThan(pageListAppium, 1, "Not enough articles found after search", 5);
        MainPageObject.waitForElementAndClick(goBackArrow, "Cannot find arrow");
        MainPageObject.waitForElementNotPresent(goBackArrow, "Go back arrow is still presetn on the page", 10);
    }

    @Test
    public void testSearchResultsContainsJava() {
        MainPageObject.waitForElementAndClick(skipButton, "Cannot find skip button");
        MainPageObject.waitForElementAndClick(searchInput, "Cannot find search input");
        MainPageObject.waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot type into search input");

        List<WebElement> searchResults = driver.findElements(pageListAppium);
        int numberOfResultsToCheck = searchResults.size();

        for (int i = 0; i < numberOfResultsToCheck; i++) {
            String resultText = searchResults.get(i).getText();
            assertTrue("Search result does not contain expected text: Java",
                    resultText.contains("Java"));
        }
    }
}