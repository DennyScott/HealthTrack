package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    private ImageButton ate;
    private ImageButton stats;
    private ImageButton warnings;
    private ImageButton goals;
    private ImageButton settings;
    private MediaPlayer backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        backgroundMusic = MediaPlayer.create(this, R.raw.delta);
        backgroundMusic.start();
        ate = (ImageButton)findViewById(R.id.ateButton);
        stats = (ImageButton)findViewById(R.id.statsButton);
        warnings = (ImageButton)findViewById(R.id.warningButton);
        goals = (ImageButton)findViewById(R.id.goalsButton);
        settings = (ImageButton)findViewById(R.id.settingsButton);
    }

    public void ateButton(View view) {
        Intent gameMode = new Intent(this, Ate.class);
        startActivity(gameMode);
    }

    public void statsButton(View view) {
        Intent gameMode = new Intent(this, StatsGui.class);
        startActivity(gameMode);
    }

    public void warningsButton(View view) {
        Intent gameMode = new Intent(this, Warnings.class);
        startActivity(gameMode);
    }

    public void goalsButton(View view) {
        Intent gameMode = new Intent(this, Goals.class);
        startActivity(gameMode);
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
}
