package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        ADD_TO_LIST_BUTTON = "id:Сохранить на потом";
        JAVA_ARTICLE_TITLE = "id:Java (programming language)";
        CLOSE_ARTICLE_BUTTON = "id:Назад";
    }

    public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
