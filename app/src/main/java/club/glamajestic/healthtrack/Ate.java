package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <code>Ate</code> is an <code>Activity</code> that shows information on what the user has eaten.
 */
public class Ate extends Activity {

    private Button foodEntryButton;
    private Button customFoodButton;

    // private Button saveButton;
    //private EditText editText;
    //private TextView textView;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ate);
        foodEntryButton = (Button)findViewById(R.id.foodEntryButton);
        //foodEntryButton.setOnClickListener(this);
        customFoodButton = (Button)findViewById(R.id.goalsSubmissionButton);
        //customFoodButton.setOnClickListener(this);

        //////////entry
        //saveButton = (Button)findViewById(R.id.saveButton);
        //saveButton.setOnClickListener(this);
        //editText = (EditText)findViewById(R.id.targetWeightTextEntry);
        //textView = (TextView)findViewById(R.id.textView2);
        //textView.setVisibility(View.GONE);
    }

    public void onBackPressed() {

        //Intent gameMode = new Intent(this, mainStats.class);
        //startActivity(gameMode);
        finish();
    }

    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, SettingsActivity.class);
        startActivity(gameMode);
        finish();
    }

    /**
     * {@inheritDoc}
     */
    //@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.foodEntryButton:
                foodEntryClick();
                break;
            case R.id.customFoodButton:
                customFoodClick();
                break;
        }
    }

    private void foodEntryClick() {
//        Intent gameMode = new Intent(this, foodEntry.class);
//        startActivity(gameMode);
    }

    private void customFoodClick() {
//       Intent gameMode = new Intent(this, customFood.class);
//        startActivity(gameMode);
    }
}
