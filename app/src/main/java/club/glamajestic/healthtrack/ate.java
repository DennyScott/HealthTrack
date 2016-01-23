package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Khaled on 1/23/2016.
 */
public class ate extends Activity {
    private int backPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ate);
    }
    public void onBackPressed() {
        if (backPressed == 0) {
            backPressed++;
            String text = "Press back again to go return to Main Scren!";
            Toast info = new Toast(this);
            info.makeText(this, text, Toast.LENGTH_LONG).show();
        } else {
            Intent gameMode = new Intent(this, mainScreen.class);
            startActivity(gameMode);
            finish();
        }
    }
}
