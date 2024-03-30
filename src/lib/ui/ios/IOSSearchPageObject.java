package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_X_BUTTON = "xpath://XCUIElementTypeButton[@name='Clear text']";
        SKIP_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Skip']";
        GET_SEARCH_RESULT_LOCATOR = "xpath://XCUIElementTypeCollectionView//XCUIElementTypeOther[1]";
        EMPTY_SEARCH_RESULT = "xpath://XCUIElementTypeStaticText[@name='Ничего не найдено']";
        PAGE_LIST_APPIUM = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    }

    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
