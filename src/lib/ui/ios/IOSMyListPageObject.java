package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import lib.ui.MyListsPageObject;

public class IOSMyListPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE = "xpath://XCUIElementTypeStaticText[@name='Java']";
        PAGE_LIST_TITLE = "id:org.wikipedia:id/page_list_item_title";
        GO_BACK_ARROW = "xpath://*[@content-desc='Navigate up']";
        CHOOSE_SAVE_FOLDER_BUTTON = "id:org.wikipedia:id/item_title";
        SAVED_TAB_BUTTON = "xpath://*[@content-desc='Saved']";
        LIST_THE_SAVED = "xpath://*[contains(@resource-id,'item_title_container')][2]";
    }

    public IOSMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
