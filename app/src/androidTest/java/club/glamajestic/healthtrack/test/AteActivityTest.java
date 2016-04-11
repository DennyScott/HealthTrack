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
        //Wait for activity: 'raw.deprecated.Ate'
		//assertTrue("raw.deprecated.Ate is not found!", solo.waitForActivity(raw.deprecated.Ate.class));
        //Click on Enter Food
		solo.clickOnView(solo.getView(club.glamajestic.healthtrack.R.id.foodEntryButton));
		//Wait for activity: 'raw.deprecated.Ate'
		assertTrue("club.glamajestic.healthtrack.FoodEntry is not found!", solo.waitForActivity(club.glamajestic.healthtrack.FoodEntry.class));
		//Click on first field
		solo.clickOnEditText(0);
		//Enter Test Name
		solo.enterText(0, "One Hundred Sauce");
		//Click on first field
		solo.clickOnEditText(1);
		//Enter Test Name
		solo.enterText(1, "100");
		//Click on first field
		solo.clickOnEditText(2);
		//Enter Test Name
		solo.enterText(2, "100");
		//Click on first field
		solo.clickOnEditText(3);
		//Enter Test Name
		solo.enterText(3, "100");
		//Click on first field
		solo.clickOnEditText(4);
		//Enter Test Name
		solo.enterText(4, "100");
		//Click on first field
		solo.clickOnEditText(5);
		//Enter Test Name
		solo.enterText(5, "100");
		//Click on first field
		solo.clickOnEditText(7);
		//Enter Test Name
		solo.enterText(7, "100");

        //Click on Custom Food Entry
		solo.clickOnView(solo.getView(R.id.saveButton));
		//Press menu back key
		solo.goBack();

        //Wait for activity: 'club.glamajestic.healthtrack.SettingsActivity'
		assertTrue("club.glamajestic.healthtrack.MainActivity is not found!", solo.waitForActivity(club.glamajestic.healthtrack.MainActivity.class));

        //Press menu back key
		solo.goBack();
	}
}
