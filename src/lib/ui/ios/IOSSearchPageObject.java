package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
        GET_SEARCH_RESULT_LOCATOR = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        EMPTY_SEARCH_RESULT = "xpath://*[@text='No results']";
        PAGE_LIST_APPIUM = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    }

    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
