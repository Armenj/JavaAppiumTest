package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject {
    private static final String
            PAGE_LIST_TITLE = "id:org.wikipedia:id/page_list_item_title",
            GO_BACK_ARROW = "xpath://*[@content-desc='Navigate up']",
            CHOOSE_SAVE_FOLDER_BUTTON = "id:org.wikipedia:id/item_title",
            SAVED_TAB_BUTTON = "xpath://*[@content-desc='Saved']",
            LIST_THE_SAVED = "xpath://*[contains(@resource-id,'item_title_container')][2]";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderByNameLocator = "xpath://*[@text='" + nameOfFolder + "']";
        this.waitForElementAndClick(folderByNameLocator, "Cannot find folder with name " + nameOfFolder, 5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        String articleTitleLocator = "xpath://*[@text='" + articleTitle + "']";
        this.swipeElementLeftToRight(articleTitleLocator, "Cannot find article with title: " + articleTitle);
        this.waitForElementNotPresent(articleTitleLocator, "Article with title " + articleTitle + " is still present after deletion", 5);
    }

    public void selectSavedFolderAndReturnHome(String folderName) {
        this.waitForElementAndClick(CHOOSE_SAVE_FOLDER_BUTTON, "Cannot find the folder to save the article", 5);
        this.waitForElementAndClick(GO_BACK_ARROW, "Cannot navigate back to the main screen", 5); // Предполагаем, что одного нажатия достаточно. Адаптируйте по необходимости.
        this.waitForElementAndClick(SAVED_TAB_BUTTON, "Cannot find Saved tab button", 5);
        this.waitForElementAndClick(LIST_THE_SAVED, "Cannot find list in Saved tab", 5);
    }
}