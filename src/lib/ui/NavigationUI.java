package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {
     protected static String
            GO_BACK_ARROW_LOCATOR,
            SAVED_TAB_BUTTON_LOCATOR;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void closeArticle() {
        this.waitForElementAndClick(GO_BACK_ARROW_LOCATOR, "Cannot find arrow");
    }

    public void clickOnTheSavedButton() {
        this.waitForElementAndClick(SAVED_TAB_BUTTON_LOCATOR, "Cannot find saved button");
    }
}