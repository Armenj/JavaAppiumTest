package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidNaviggationUI;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSNavigationUI;
import lib.ui.ios.IOSSearchPageObject;

public class NavigationUIFactory {
    public static NavigationUI get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidNaviggationUI(driver);
        }else {
            return new IOSNavigationUI(driver);
        }
    }
}
