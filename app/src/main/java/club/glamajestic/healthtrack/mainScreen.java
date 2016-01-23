package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class mainScreen extends Activity {

    private ImageButton ate;
    private ImageButton stats;
    private ImageButton warnings;
    private ImageButton goals;
    private ImageButton settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        ate = (ImageButton)findViewById(R.id.ateButton);
        stats = (ImageButton)findViewById(R.id.statsButton);
        warnings = (ImageButton)findViewById(R.id.warningButton);
        goals = (ImageButton)findViewById(R.id.goalsButton);
        settings = (ImageButton)findViewById(R.id.settingsButton);

    }
    public void ateButton(View view) {
        Intent gameMode = new Intent(this, ate.class);
        startActivity(gameMode);
        finish();
    }
    public void statsButton(View view) {
        Intent gameMode = new Intent(this, stats.class);
        startActivity(gameMode);
        finish();
    }
    public void warningsButton(View view) {
        Intent gameMode = new Intent(this, warnings.class);
        startActivity(gameMode);
        finish();
    }
    public void goalsButton(View view) {
        Intent gameMode = new Intent(this, goals.class);
        startActivity(gameMode);
        finish();
    }
    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, settings.class);
        startActivity(gameMode);
        finish();
    }

}
