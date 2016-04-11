package club.glamajestic.healthtrack.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import club.glamajestic.healthtrack.R;
import club.glamajestic.healthtrack.Splash;


public class MainActivityTest extends ActivityInstrumentationTestCase2<Splash> {
    private Solo solo;

    public MainActivityTest() {
        super(Splash.class);
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
        //Wait for activity: 'club.glamajestic.healthtrack.Splash'
        solo.waitForActivity(club.glamajestic.healthtrack.Splash.class, 4000);
        //Wait for activity: 'club.glamajestic.healthtrack.StatsGuiActivity'
        assertTrue("club.glamajestic.healthtrack.MainActivity is not found!", solo.waitForActivity(club.glamajestic.healthtrack.MainActivity.class));
        //Click on ImageView
        solo.clickOnView(solo.getView(R.id.chartFrame));
        //Click on Food Journal FrameLayout
        //Click on Enter Food
        solo.clickOnView(solo.getView(R.id.monthButton));
        //Click on Custom Food Entry
        solo.clickOnView(solo.getView(R.id.weekButton));
        //Click on ImageView
        solo.clickOnView(solo.getView(R.id.dayButton));
        //Click on ImageView
        solo.clickOnView(solo.getView(R.id.barChartTab));
        //Press menu back key
        solo.goBack();
        //Wait for activity: 'club.glamajestic.healthtrack.SettingsActivity'
        assertTrue("club.glamajestic.healthtrack.MainActivity is not found!", solo.waitForActivity(club.glamajestic.healthtrack.MainActivity.class));
        //Press menu back key
        solo.goBack();
    }
}
