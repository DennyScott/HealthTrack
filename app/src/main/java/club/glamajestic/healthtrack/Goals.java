package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Khaled on 1/23/2016.
 */
public class Goals extends Activity {
    private int backPressed = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);
    }

    public void onBackPressed() {
        Intent gameMode = new Intent(this, mainStats.class);
        startActivity(gameMode);
        finish();
    }
    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, Settings.class);
        startActivity(gameMode);
        finish();
    }
}
