package club.glamajestic.healthtrack.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import club.glamajestic.healthtrack.R;
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
		assertTrue("club.glamajestic.healthtrack.FoodEntry is not found!", solo.waitForActivity(club.glamajestic.healthtrack.FoodEntry.class));
		solo.clickOnView(solo.getView(R.id.foodSearchView));
		solo.enterText(0, "donut");
		//solo.clickOnView(solo.getView(R.id.foodSearchView));
		solo.clickOnView(solo.getView(R.id.foodEntryScrollableLayout));
		solo.enterText(0, "5");
		solo.clickOnView(solo.getView(R.id.saveButton));
		//Press menu back key
		//solo.goBack();
        //Wait for activity: 'club.glamajestic.healthtrack.SettingsActivity'
		assertTrue("club.glamajestic.healthtrack.MainActivity is not found!", solo.waitForActivity(club.glamajestic.healthtrack.MainActivity.class));

        //Press menu back key
		solo.goBack();
	}
}
