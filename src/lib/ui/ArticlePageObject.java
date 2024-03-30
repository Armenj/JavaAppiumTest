package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

import static junit.framework.TestCase.assertEquals;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            FOOTER_ELEMENT,
            SAVE_ARTICLE_BUTTON,
            ADD_TO_LIST_BUTTON,
            NAME_OF_THE_LIST_AREA ,
            OK_BUTTON_ON_THE_POPUP,
            CLOSE_ARTICLE_BUTTON,
            JAVA_ARTICLE_TITLE;


    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(String title){
        String dynamicTitleLocator;
        if(Platform.getInstance().isAndroid()){
            dynamicTitleLocator = "xpath://*[@text='" + title + "']";
        } else {
            // Для iOS мы попробуем найти элемент по атрибуту name или label
            dynamicTitleLocator = "xpath://*[contains(@name,'" + title + "') or contains(@label,'" + title + "')]";
        }
        return this.waitForElementToBeVisible(dynamicTitleLocator, "Cannot find article title on the page: " + title, 20);
    }


    public String getArticleTitle(String title) {
        WebElement title_element = waitForTitleElement(title);
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            // Попытка получить текст статьи через различные атрибуты
            String nameAttribute = title_element.getAttribute("name");
            if (nameAttribute == null || nameAttribute.isEmpty()) {
                // Если атрибут name не содержит текста, пробуем label
                String labelAttribute = title_element.getAttribute("label");
                if (labelAttribute == null || labelAttribute.isEmpty()) {
                    // Если атрибут label также не содержит текста, пробуем value
                    return title_element.getAttribute("value");
                } else {
                    return labelAttribute;
                }
            } else {
                return nameAttribute;
            }
        }
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

    public void addArticleToMySaved(){
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON, "Cannot find napotom button");
    }
}