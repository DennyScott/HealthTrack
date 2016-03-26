package club.glamajestic.healthtrack.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import club.glamajestic.healthtrack.Splash;


public class AteActivityTest extends ActivityInstrumentationTestCase2<Splash> {
  	private Solo solo;
  	
  	public AteActivityTest() {
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
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on Food Journal FrameLayout
		solo.clickInRecyclerView(1, 0);
        //Wait for activity: 'club.glamajestic.healthtrack.Ate'
		assertTrue("club.glamajestic.healthtrack.Ate is not found!", solo.waitForActivity(club.glamajestic.healthtrack.Ate.class));
        //Click on Enter Food
		solo.clickOnView(solo.getView(club.glamajestic.healthtrack.R.id.foodEntryButton));
        //Click on Custom Food Entry
		solo.clickOnView(solo.getView(club.glamajestic.healthtrack.R.id.customFoodButton));
        //Click on ImageView
		solo.clickOnView(solo.getView(club.glamajestic.healthtrack.R.id.settingsButton));
        //Wait for activity: 'club.glamajestic.healthtrack.SettingsActivity'
		assertTrue("club.glamajestic.healthtrack.SettingsActivity is not found!", solo.waitForActivity(club.glamajestic.healthtrack.SettingsActivity.class));
        //Press menu back key
		solo.goBack();
        //Press menu back key
		solo.goBack();
	}
}
