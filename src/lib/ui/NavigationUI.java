package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {
    private static final String
            GO_BACK_ARROW_LOCATOR = "xpath://*[@content-desc='Navigate up']",
            SAVED_TAB_BUTTON_LOCATOR = "xpath:(//*[@resource-id='org.wikipedia:id/navigation_bar_item_icon_view'])[2]";

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