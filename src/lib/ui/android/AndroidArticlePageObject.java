package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        SAVE_ARTICLE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        NAME_OF_THE_LIST_AREA = "id:org.wikipedia:id/text_input";
        OK_BUTTON_ON_THE_POPUP = "id:android:id/button1";
        JAVA_ARTICLE_TITLE = "xpath://*[@text='Java (programming language)']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
