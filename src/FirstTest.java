import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class FirstTest {

    private AndroidDriver<WebElement> driver;
    private By skipButton = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private By searchInput = By.xpath("//*[@text='Search Wikipedia']");
    private By toIntoSearch = By.id("org.wikipedia:id/search_src_text");
    private By javaArticleSearchResult = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']");
    private By getSearchResultLocator = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup");
    private By goBackArrow = By.xpath("//*[@content-desc='Navigate up']");
    private By javaArticleTitle = By.xpath("//android.widget.TextView[@text='Java (programming language)']");
    private By javaText = By.id("org.wikipedia:id/search_src_text");
    private By pageListAppium = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Appium']");
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

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "8.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "C:/Users/ameliksetyan/Desktop/JavaAppiumTest/apks/org.wikipedia_50467_apps.evozi.com.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSaveTwoArticlesAndRemoveOne(){
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        waitForElementAndClick(javaArticleSearchResult, "Result not present");
        waitForElementAndClick(saveArticleButton, "Cannot find more_options button");
        waitForElementAndClick(addToListButton, "Cannot find add to list button");
        waitForElementAndSendKeys(nameOfTheListArea, "Homework", "Cannot find area");
        waitForElementAndClick(okButtonOnThePopUp, "Cannot find ok button on the pop-up");

        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Appium", "Cannot find search result");
        waitForElementAndClick(appiumArticleSearchResult, "Result not present");
        waitForElementAndClick(saveArticleButton, "Cannot find more_options button");
        waitForElementAndClick(addToListButton, "Cannot find add to list button");

        waitForElementAndClick(chooseSaveFolderButton, "Cannot find the folder to save the article");

        for (int i = 0; i < 3; i++) {
            waitForElementAndClick(goBackArrow, "Cannot find arrow");
        }
        waitForElementAndClick(savedTabButton, "Cannot find saved button");
        waitForElementAndClick(listOnTheSaved, "Cannot find list on the saved page");
        swipeElementLeftToRight(pageListAppium, "Cannot find page list");

        waitForElementPresent(javaArticleSearchResult, "Article search result not present");
        waitForElementAndClick(javaArticleSearchResult, "Cannot click on article search result");

        WebElement titleElement = waitForElementToBeVisible(javaArticleTitle, "Article title not found", 10);
        String title = titleElement.getAttribute("text");
        System.out.println(title);
        Assert.assertEquals(
                "we see unexpected tittle",
                "Java (programming language)",
                title
        );
    }

    @Test
    public void testArticleHasTitle(){
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        waitForElementAndClick(javaArticleSearchResult, "Result not present");
        assertElementPresent(javaArticleTitle, "Article title is not present on the page without waiting");
    }

    @Test
    public void testSearchInputText() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        assertElementHasText(searchInput, "Search Wikipedia", "Search input does not contain the expected text.");
    }

    @Test
    public void searchResultsMoreThanOne() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        waitForNumberOfElementsToBeMoreThan(pageListAppium, 1, "Not enough articles found after search", 5);
        waitForElementAndClick(goBackArrow, "Cannot find arrow");
        waitForElementNotPresent(goBackArrow, "Go back arrow is still presetn on the page", 10);
    }

    @Test
    public void searchResultsContainsJava() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot type into search input");

        List<WebElement> searchResults = driver.findElements(pageListAppium);
        int numberOfResultsToCheck = searchResults.size();

        for (int i = 0; i < numberOfResultsToCheck; i++) {
            String resultText = searchResults.get(i).getText();
            Assert.assertTrue("Search result does not contain expected text: Java",
                    resultText.contains("Java"));
        }
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result", 5);
        waitForElementPresent(javaArticleSearchResult, "Result not present", 10);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        waitForElementAndClear(javaText, "Cannot find search field", 5);
        waitForElementAndClick(goBackArrow, "Cannot find arrow");
        waitForElementNotPresent(goBackArrow, "Go back arrow is still presetn on the page", 10);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        waitForElementAndClick(javaArticleSearchResult, "Result not present");
        WebElement titleElement = waitForElementToBeVisible(javaArticleTitle, "Article title not found", 10);
        String title = titleElement.getAttribute("text");
        System.out.println(title);
        Assert.assertEquals(
                "we see unexpected tittle",
                "Java (programming language)",
                title
        );
    }

    @Test
    public void testSwipeArticle() throws InterruptedException {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Appium", "Cannot find search result");
        waitForElementAndClick(appiumArticleSearchResult, "Result not present", 10);
        Thread.sleep(5000);
        verticalSwipeToBottom();
        swipeUpToFindElement(pageFuter, "Cannot find the end of the article", 3);
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result");
        waitForElementAndClick(javaArticleSearchResult, "Result not present");
        waitForElementAndClick(saveArticleButton, "Cannot find more_options button");
        waitForElementAndClick(addToListButton, "Cannot find add to list button");
        waitForElementAndSendKeys(nameOfTheListArea, "Test", "Cannot find area");
        waitForElementAndClick(okButtonOnThePopUp, "Cannot find ok button on the pop-up");
        waitForElementAndClick(goBackArrow, "Cannot find arrow");
        waitForElementAndClick(goBackArrow, "Cannot find arrow");
        waitForElementAndClick(savedTabButton, "Cannot find saved button");
        waitForElementAndClick(listOnTheSaved, "Cannot find list on the saved page");
        swipeElementLeftToRight(pageListAppium, "Cannot find page list");
        waitForElementNotPresent(pageListAppium, "Cannot delete saved article ", 5);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        String search_line = "Linkin Park Diskography";
        waitForElementAndSendKeys(toIntoSearch, search_line, "Cannot find search result");
        waitForElementPresent(getSearchResultLocator, "Cannot find anithing by the request" + search_line, 15);
        int amount_of_search_result = getAmountOfElements(getSearchResultLocator);
        System.out.println("Amount of search results: " + amount_of_search_result);
        Assert.assertTrue("We found too few results", amount_of_search_result > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        String search_line = "Java";
        waitForElementAndSendKeys(toIntoSearch, search_line, "Cannot find search result");
        waitForElementNotPresent(emptyResultLabel, "Cannot find empty result label by the request", 5);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        String search_line = "Java";
        waitForElementAndSendKeys(toIntoSearch, search_line, "Cannot find search result");
        waitForElementAndClick(javaArticleSearchResult, "Result not present");
        String title_before_rotation = waitForElementAndGetAttribute(javaTitle, "text", "Cannot find title of article", 15);
        System.out.println(title_before_rotation);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = waitForElementAndGetAttribute(javaTitle, "text", "Cannot find title of article", 15);
        Assert.assertEquals("Article title have been changed after screen rotation", title_before_rotation, title_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndGetAttribute(javaTitle, "text", "Cannot find title of article", 15);
        Assert.assertEquals("Article title have been changed after screen rotation", title_before_rotation, title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        String search_line = "Java";
        waitForElementAndSendKeys(toIntoSearch, search_line, "Cannot find search result");
        waitForElementPresent(javaArticleSearchResult, "Cannot find search result");
        driver.runAppInBackground(Duration.ofSeconds(2));
        waitForElementPresent(javaArticleSearchResult, "Cannot find article after returning from background");
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' suposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementPresent(By by, String error_message) {
        int element_count = getAmountOfElements(by);
        if (element_count == 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private void waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
    }

    private void waitForElementAndClick(By by, String error_message) {
        waitForElementAndClick(by, error_message, 5);
    }

    private void waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
    }

    private void waitForElementAndSendKeys(By by, String value, String error_message) {
        waitForElementAndSendKeys(by, value, error_message, 5);
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementToBeVisible(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String expectedText, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String actualText = element.getAttribute("text");
        Assert.assertEquals(error_message, expectedText, actualText);
    }

    private void waitForNumberOfElementsToBeMoreThan(By by, int expectedNumber, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        wait.until((WebDriver d) -> d.findElements(by).size() > expectedNumber);
    }

    public void verticalSwipeToBottom() {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int centerX = size.width / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), centerX, (int) startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), centerX, (int) endY));
        swipe.addAction(finger.createPointerUp(0));
        driver.perform(Arrays.asList(swipe));
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swipping up. \n" + error_message, 0);
                return;
            }
            verticalSwipeToBottom();
            ++already_swiped;
        }
    }

    public void swipeElementLeftToRight(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int centerY = (upperY + lowerY) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), leftX, centerY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), rightX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
}