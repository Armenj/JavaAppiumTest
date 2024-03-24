package tests.IOS;

import lib.IOSTestCase;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends IOSTestCase {

    public void testPassThroughWelcome(){
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
