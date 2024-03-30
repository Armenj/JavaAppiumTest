package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase {

    public void testPassThroughWelcome(){
        if(Platform.getInstance().isAndroid()){
            return;
        }

        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();
//
//        welcomePage.waitForNewWayToExplore();
//        welcomePage.clickNextButton();
//
//        welcomePage.waitForAddOrEditPreferredLangText();
//        welcomePage.clickNextButton();
//
//        welcomePage.waitForLearnMoreAboutDataCollected();
//        welcomePage.clickGetStartedButton();
    }
}
