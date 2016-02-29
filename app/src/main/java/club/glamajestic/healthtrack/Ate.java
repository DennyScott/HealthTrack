package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * <code>Ate</code> is an <code>Activity</code> that shows information on what the user has eaten.
 */
public class Ate extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ate);
    }

    /**
     * Calls <code>finish()</code> to close this <code>Activity</code>, returning to previous
     * <code>Activity</code> on the stack.
     */
    public void onBackPressed() {

        Intent gameMode = new Intent(this, mainStats.class);
        startActivity(gameMode);
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
        finish();
    }
}
