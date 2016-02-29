package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * <code>Goals</code> is an <code>Activity</code> that allows users to set and manage goals
 * based on relevant user information.
 */
public class Goals extends Activity implements View.OnClickListener {

    private Button goalsUserInfoButton;
    private Button goalsSubmissionButton;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);
        goalsUserInfoButton = (Button)findViewById(R.id.goalsUserInfoButton);
        goalsUserInfoButton.setOnClickListener(this);
        goalsSubmissionButton = (Button)findViewById(R.id.goalsSubmissionButton);
        goalsSubmissionButton.setOnClickListener(this);
    }

    /**
     * Calls <code>finish()</code> to close this <code>Activity</code>, returning to previous
     * <code>Activity</code> on the stack.
     */
    public void onBackPressed() {
        Intent gameMode = new Intent(this, mainStats.class);
        startActivity(gameMode);
        finish();
        Output.toastMessage(this, "Returning to previous screen.", Output.LONG_TOAST);
        finish();
    }

    /**
     * Creates a new <code>Intent</code> and starts <code>SettingsActivity</code>.
     *
     * @param view Unused.
     */
    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, SettingsActivity.class);
        startActivity(gameMode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goalsUserInfoButton:
                goalsUserInfoClick();
                break;
            case R.id.goalsSubmissionButton:
                goalsUserSubmissionClick();
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
