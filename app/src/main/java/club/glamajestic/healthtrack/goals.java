package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Khaled on 1/23/2016.
 */
public class goals extends Activity {
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
            Output.toastMessage(this, message);
        } else {
            Intent gameMode = new Intent(this, mainScreen.class);
            startActivity(gameMode);
            finish();
        }
    }
}
