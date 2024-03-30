package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidMyListsPageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSMyListPageObject;

public class MyListsPageObjectFactories {
    public static MyListsPageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        }else {
            return new IOSMyListPageObject(driver);
        }
    }
}
