package club.glamajestic.healthtrack.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import club.glamajestic.healthtrack.Goals;
import club.glamajestic.healthtrack.R;


/**
 * Created by scott on 03/04/16.
 */

public class GoalsTest extends ActivityInstrumentationTestCase2<Goals> {
    private Solo solo;

    public GoalsTest() {
        super(Goals.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testRun(){
        //Wait for activity: 'club.glamajestic.healthtrack.StatsGuiActivity'
        assertTrue("club.glamajestic.healthtrack.Goals is not found!", solo.waitForActivity(club.glamajestic.healthtrack.Goals.class));
        //Click on Enter Food
        solo.clickOnView(solo.getView(club.glamajestic.healthtrack.R.id.goalsUserInfoButton));
        //Wait for activity: 'club.glamajestic.healthtrack.StatsGuiActivity'
        assertTrue("club.glamajestic.healthtrack.GoalsUserInfo is not found!", solo.waitForActivity(club.glamajestic.healthtrack.GoalsUserInfo.class));
        //Click on first field
        solo.clickOnEditText(0);
        //Enter Test Name
        solo.enterText(0, "Kakarotto");
        //Click on second field
        solo.clickOnEditText(1);
        //Enter Test Name
        solo.enterText(1, "40");
        //Click on first field
        solo.clickOnEditText(2);
        //Enter Test Name
        solo.enterText(2, "137");
        //Click on first field
        solo.clickOnEditText(3);
        //Enter Test Name
        solo.enterText(3, "175");
        //Click on Male Female radio button
        solo.clickOnRadioButton(0);
        //Click on Save button
        solo.clickOnView(solo.getView(R.id.saveButton));
        //Wait for activity: 'club.glamajestic.healthtrack.SettingsActivity'
        assertTrue("Error Saving changes (or displaying toast message)", solo.waitForText("Changes updated."));
        //Wait for activity: 'club.glamajestic.healthtrack.StatsGuiActivity'
        assertTrue("club.glamajestic.healthtrack.MainActivity is not found!", solo.waitForActivity(club.glamajestic.healthtrack.MainActivity.class));
        //Press menu back key
        solo.goBack();
    }
}
