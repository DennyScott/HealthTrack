package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * <code>GoalsSubmission</code> is an <code>Activity</code> that allows users to create
 * health-related goals.
 */
public class GoalsSubmission extends Activity implements View.OnClickListener {

    private Button saveButton;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_submission_page);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    /**
     * Calls <code>finish()</code> to close this <code>Activity</code>, returning to previous
     * <code>Activity</code> on the stack.
     */
    public void onBackPressed() {
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
            case R.id.saveButton:
                saveClicked();
                break;
        }
    }

    private void saveClicked() {
        Output.toastMessage(this, "Changes updated.", Output.SHORT_TOAST);
    }

}