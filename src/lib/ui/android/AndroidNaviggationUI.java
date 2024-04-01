package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNaviggationUI extends NavigationUI {
    static {
        GO_BACK_ARROW_LOCATOR = "xpath://*[@content-desc='Navigate up']";
        SAVED_TAB_BUTTON_LOCATOR = "xpath:(//*[@resource-id='org.wikipedia:id/navigation_bar_item_icon_view'])[2]";
    }

    public AndroidNaviggationUI(AppiumDriver driver){
        super(driver);
    }
}
