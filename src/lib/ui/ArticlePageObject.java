package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static junit.framework.TestCase.assertEquals;

public class ArticlePageObject extends MainPageObject {

    private static final By
            FOOTER_ELEMENT = By.xpath("//*[@text='View article in browser']"),
            SAVE_ARTICLE_BUTTON = By.id("org.wikipedia:id/page_save"),
            ADD_TO_LIST_BUTTON = By.id("org.wikipedia:id/snackbar_action"),
            NAME_OF_THE_LIST_AREA = By.id("org.wikipedia:id/text_input"),
            OK_BUTTON_ON_THE_POPUP = By.id("android:id/button1"),
            JAVA_ARTICLE_TITLE = By.xpath("//*[@text='Java (programming language)']");


    public ArticlePageObject(AndroidDriver driver){
        super(driver);
    }

    // Измененный метод, принимающий название статьи в качестве параметра
    public WebElement waitForTitleElement(String title){
        By dynamicTitle = By.xpath("//android.widget.TextView[@text=\"" + title + "\"]");
        return this.waitForElementToBeVisible(dynamicTitle, "Cannot find article title on the page: " + title, 20);
    }

    // Измененный метод, который теперь также требует название статьи
    public String getArticleTitle(String title){
        WebElement title_element = waitForTitleElement(title);
        return title_element.getAttribute("text");
    }

    public String getArticleTitleAndValidate(String expectedTitle) {
        WebElement titleElement = this.waitForElementToBeVisible(JAVA_ARTICLE_TITLE, "Article title not found", 10);
        String title = titleElement.getAttribute("text");
        System.out.println(title);
        assertEquals("We see unexpected title", expectedTitle, title);
        return title;
    }

    public void swipeToFooter(){
        this.verticalSwipeToBottom();
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of the article", 3);
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "Cannot find more_options button");
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON, "Cannot find add to list button");
        this.waitForElementAndSendKeys(NAME_OF_THE_LIST_AREA, name_of_folder, "Cannot find area");
        this.waitForElementAndClick(OK_BUTTON_ON_THE_POPUP, "Cannot find ok button on the pop-up");
    }

    public void addAnotherArticleToMyList(){
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "Cannot find more_options button");
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON, "Cannot find add to list button");
    }

    public void assertArticleTitlePresent() {
        this.assertElementPresent(JAVA_ARTICLE_TITLE, "Article title is not present on the page without waiting");
    }
}