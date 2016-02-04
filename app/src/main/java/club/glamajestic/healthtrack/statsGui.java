package club.glamajestic.healthtrack;

/**
 * Created by Khaled on 1/22/2016.
**/
import business.stats;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class statsGui extends Activity{
    private int backPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
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
