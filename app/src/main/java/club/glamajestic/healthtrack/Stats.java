package club.glamajestic.healthtrack;

/**
 * Created by Khaled on 1/22/2016.
**/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Stats extends Activity{
    private int backPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
    }

    public void onBackPressed() {
        String message = "Press back again to return to main screen.";

        if (backPressed == 0) {
            backPressed++;
            Output.toastMessage(this, message);
        } else {
            Intent gameMode = new Intent(this, MainActivity.class);
            startActivity(gameMode);
            finish();
        }
    }
}