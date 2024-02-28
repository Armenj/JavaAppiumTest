import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AndroidDriver<WebElement> driver;
    private By skipButton = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private By searchInput = By.xpath("//*[@text='Search Wikipedia']");
    private By toIntoSearch = By.id("org.wikipedia:id/search_src_text");
    private By searchResult = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']");
    private By goBackArrow = By.xpath("//*[@content-desc='Navigate up']");
    private By articleTitle = By.xpath("//android.widget.TextView[@text='Java (programming language)']");
    private By javaText = By.id("org.wikipedia:id/search_src_text");

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
    public void firstTest() {
        waitForElementAndClick(skipButton, "Cannot find skip button");
        waitForElementAndClick(searchInput, "Cannot find search input");
        waitForElementAndSendKeys(toIntoSearch, "Java", "Cannot find search result", 5);
        waitForElementPresent(searchResult, "Result not present", 10);
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
        waitForElementAndClick(searchResult, "Result not present");
        WebElement titleElement = waitForElementToBeVisible(articleTitle, "Article title not found", 10);
        String title = titleElement.getAttribute("text");
        System.out.println(title);
        Assert.assertEquals(
                "we see unexpected tittle",
                "Java (programming language)",
                title
        );
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
}