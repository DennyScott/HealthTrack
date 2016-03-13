package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * <code>Ate</code> is an <code>Activity</code> that shows information on what the user has eaten.
 */
public class Ate extends Activity {

    private Button foodEntryButton;
    private Button customFoodButton;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ate);
        foodEntryButton = (Button)findViewById(R.id.foodEntryButton);
        //foodEntryButton.setOnClickListener(this);
        customFoodButton = (Button)findViewById(R.id.goalsSubmissionButton);
        //customFoodButton.setOnClickListener(this);
    }

    public void onBackPressed() {

        //Intent gameMode = new Intent(this, mainStats.class);
        //startActivity(gameMode);
        finish();
    }

    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, SettingsActivity.class);
        startActivity(gameMode);
        finish();
    }

    /**
     * {@inheritDoc}
     */
    //@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.foodEntryButton:
               // goalsUserInfoClick();
                break;
            case R.id.customFoodButton:
               // goalsUserSubmissionClick();
                break;
        }
    }

    private void goalsUserInfoClick() {
        Intent gameMode = new Intent(this, GoalsUserInfo.class);
        startActivity(gameMode);
    }

    private void goalsUserSubmissionClick() {
        Intent gameMode = new Intent(this, GoalsSubmission.class);
        startActivity(gameMode);
    }
}
