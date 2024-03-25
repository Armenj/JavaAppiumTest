package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase {

    public void testPassThroughWelcome(){
        if(this.Platform.isAndroid()){
            return;
        }

        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWayToExplore();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferredLangText();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDataCollected();
        welcomePage.clickGetStartedButton();
    }
}
