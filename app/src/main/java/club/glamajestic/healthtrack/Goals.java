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
        String message = "Press back again to return to main screen.";

        if (backPressed == 0) {
            backPressed++;
            String text = "Press back again to go return to Main Screen!";
            Toast info = new Toast(this);
            info.makeText(this, text, Toast.LENGTH_LONG).show();
        } else {
            Intent gameMode = new Intent(this, MainActivity.class);
            startActivity(gameMode);
            finish();
        }
    }
    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, settings.class);
        startActivity(gameMode);
        finish();
    }
}
