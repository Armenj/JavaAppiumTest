package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI{
    static {
        SAVED_TAB_BUTTON_LOCATOR = "id:Сохранить на потом";
    }

    public IOSNavigationUI(AppiumDriver driver){
        super(driver);
    }

}
