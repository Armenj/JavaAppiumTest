package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
            PAGE_LIST_TITLE,
            GO_BACK_ARROW,
            CHOOSE_SAVE_FOLDER_BUTTON,
            SAVED_TAB_BUTTON,
            ARTICLE_BY_TITLE,
            OPEN_SAVED_LIST_BUTTON,
            BACK_BATTON,
            CLEAR_TEXT,
            CLOSE_POPUP,
            SWIPE_TO_DELETE,
            CANCEL_BUTTON,
            LIST_THE_SAVED;

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderByNameLocator = "xpath://*[@text='" + nameOfFolder + "']";
        this.waitForElementAndClick(folderByNameLocator, "Cannot find folder with name " + nameOfFolder, 5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        String articleTitleLocator;
        if (Platform.getInstance().isAndroid()) {
            articleTitleLocator = "xpath://*[@text='" + articleTitle + "']";
        } else if (Platform.getInstance().isIOS()) {
            articleTitleLocator = "xpath://*[@name='" + articleTitle + "']";
        } else {
            throw new IllegalArgumentException("Unsupported platform for swipeByArticleToDelete method");
        }

        this.swipeElementLeftToRight(articleTitleLocator, "Cannot find article with title: " + articleTitle);
        this.waitForElementAndClick(SWIPE_TO_DELETE, "Cannot swipe to delete button", 5);
        this.waitForElementNotPresent(articleTitleLocator, "Article with title " + articleTitle + " is still present after deletion", 5);
    }

    public void selectSavedFolderAndReturnHome(String folderName) {
        this.waitForElementAndClick(CHOOSE_SAVE_FOLDER_BUTTON, "Cannot find the folder to save the article", 5);
        this.waitForElementAndClick(GO_BACK_ARROW, "Cannot navigate back to the main screen", 5); // Предполагаем, что одного нажатия достаточно. Адаптируйте по необходимости.
        this.waitForElementAndClick(SAVED_TAB_BUTTON, "Cannot find Saved tab button", 5);
        this.waitForElementAndClick(LIST_THE_SAVED, "Cannot find list in Saved tab", 5);
    }


    public void openSavedArticles() {
        this.waitForElementAndClick(BACK_BATTON, "Cannot find back button", 5);
        this.waitForElementAndClick(CLEAR_TEXT, "Cannot find Clear text button", 10);
        this.waitForElementAndClick(CANCEL_BUTTON, "Cannot find back button", 5);
        this.waitForElementAndClick(BACK_BATTON, "Cannot find back button", 5);
        this.waitForElementAndClick(OPEN_SAVED_LIST_BUTTON, "Cannot find OPEN_SAVED_LIST_BUTTON button");
        this.waitForElementAndClick(CLOSE_POPUP, "Cannot find close popup", 5);
    }
}