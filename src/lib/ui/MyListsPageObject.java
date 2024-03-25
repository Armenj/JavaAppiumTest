package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    private static final By
            LIST_ON_THE_SAVED = By.id("org.wikipedia:id/item_title"),
            PAGE_LIST_TITLE = By.id("org.wikipedia:id/page_list_item_title"),
            GO_BACK_ARROW = By.xpath("//*[@content-desc='Navigate up']"),
            CHOOSE_SAVE_FOLDER_BUTTON = By.id("org.wikipedia:id/item_title"),
            SAVED_TAB_BUTTON = By.xpath("(//*[@resource-id='org.wikipedia:id/navigation_bar_item_icon_view'])[2]"),
            LIST_THE_SAVED = By.xpath("(//*[@resource-id='org.wikipedia:id/item_title_container'])[2]");


    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        By folderByName = By.xpath("//*[@text='" + nameOfFolder + "']");
        this.waitForElementAndClick(folderByName, "Cannot find folder with name " + nameOfFolder);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        By articleTitleBy = By.xpath("//*[@text='" + articleTitle + "']");
        this.swipeElementLeftToRight(articleTitleBy, "Cannot find article with title: " + articleTitle);
        this.waitForElementNotPresent(articleTitleBy, "Article with title " + articleTitle + " is still present after deletion", 5);
    }

    public void selectSavedFolderAndReturnHome(String folderName) {
        this.waitForElementAndClick(CHOOSE_SAVE_FOLDER_BUTTON, "Cannot find the folder to save the article");
        for (int i = 0; i < 3; i++) {
            this.waitForElementAndClick(GO_BACK_ARROW, "Cannot find arrow");
        }
        this.waitForElementAndClick(SAVED_TAB_BUTTON, "Cannot find saved button");
        this.waitForElementAndClick(LIST_THE_SAVED, "Cannot find list on the saved page");
    }
}