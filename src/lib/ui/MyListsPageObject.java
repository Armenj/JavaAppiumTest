package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    private static final By
            LIST_ON_THE_SAVED = By.id("org.wikipedia:id/item_title"), // Уточните правильный идентификатор
            PAGE_LIST_TITLE = By.id("org.wikipedia:id/page_list_item_title"); // Уточните правильный идентификатор

    public MyListsPageObject(AndroidDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        // Изменен метод для открытия папки по названию
        By folderByName = By.xpath("//*[@text='" + nameOfFolder + "']");
        this.waitForElementAndClick(folderByName, "Cannot find folder with name " + nameOfFolder);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        // Изменен метод для удаления статьи по заголовку
        By articleTitleBy = By.xpath("//*[@text='" + articleTitle + "']");
        this.swipeElementLeftToRight(articleTitleBy, "Cannot find article with title: " + articleTitle);
        this.waitForElementNotPresent(articleTitleBy, "Article with title " + articleTitle + " is still present after deletion", 5);
    }
}