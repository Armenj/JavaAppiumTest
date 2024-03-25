package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final By
            GO_BACK_ARROW = By.xpath("//*[@content-desc='Navigate up']"),
            SAVED_TAB_BUTTON = By.xpath("(//*[@resource-id='org.wikipedia:id/navigation_bar_item_icon_view'])[2]");

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void closeArticle(){
        this.waitForElementAndClick(GO_BACK_ARROW, "Cannot find arrow");
    }

    public void clickOnTheSavedButton(){
        this.waitForElementAndClick(SAVED_TAB_BUTTON, "Cannot find saved button");
    }
}