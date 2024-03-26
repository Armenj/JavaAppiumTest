package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Узнать подробнее о Википедии']";
    private static final String NEW_WAY_TO_EXPLORE = "id:Новые способы изучения";
    private static final String ADD_OR_EDIT_PREFERRED_LANGUAGES = "xpath://XCUIElementTypeStaticText[@name='Добавить или изменить предпочтительные языки']";
    private static final String LEARN_MORE_ABOUT_DATA_COLLECTED = "xpath://XCUIElementTypeStaticText[@name='Learn more about our privacy policy and terms of use']";
    private static final String NEXT_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Далее']";
    private static final String GET_STARTED_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Начать']";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(LEARN_MORE_LINK, "Cannot find 'Узнать подробнее о Википедии' link", 10);
    }

    public void waitForNewWayToExplore() {
        this.waitForElementPresent(NEW_WAY_TO_EXPLORE, "Cannot find 'Новые способы изучения' element", 5);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(ADD_OR_EDIT_PREFERRED_LANGUAGES, "Cannot find 'Добавить или изменить предпочтительные языки' element", 4);
    }

    public void waitForLearnMoreAboutDataCollected() {
        this.waitForElementPresent(LEARN_MORE_ABOUT_DATA_COLLECTED, "Cannot find 'Learn more about our privacy policy and terms of use' text", 7);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BUTTON, "Cannot find and click 'Далее' button");
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Начать' button");
    }
}