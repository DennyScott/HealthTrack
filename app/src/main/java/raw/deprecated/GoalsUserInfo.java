package raw.deprecated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import club.glamajestic.healthtrack.MainActivity;
import club.glamajestic.healthtrack.Output;
import club.glamajestic.healthtrack.R;

/**
 * <code>GoalsUserInfo</code> allows the user to enter information about themselves, which the
 * app can then use when providing statistics and managing goals.
 */
public class GoalsUserInfo extends Activity implements View.OnClickListener {

    private Button saveButton;
    private EditText nameText;
    private EditText ageText;
    private EditText weightText;
    private EditText heightText;
    private UserDataAccess user;
    private RadioButton male, female;
    private int gender;
    private Spinner activeLevelText;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gender = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_user_info);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        nameText = (EditText)findViewById(R.id.nameTextEntry);
        ageText = (EditText)findViewById(R.id.ageTextEntry);
        weightText = (EditText)findViewById(R.id.weightTextEntry);
        heightText = (EditText)findViewById(R.id.heightTextEntry);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        activeLevelText = (Spinner)findViewById(R.id.activeSpinner);
        user = new UserDataAccess(this);
    }

    public void onBackPressed() {

        finish();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                saveClicked();
                break;
        }
    }

    private void saveClicked() {
        saveText();
    }

    public void male(View view) {
        gender= 0;
    }
    public void female(View view) {
        gender= 1;
    }

    private void saveText(){
        String name = nameText.getText().toString();
        String age = ageText.getText().toString();
        String weight = weightText.getText().toString();
        String height = heightText.getText().toString();
        String activeLevelString = activeLevelText.getSelectedItem().toString();
        int activeLevel;

        switch (activeLevelString) {
            case "Little To No Exercise": activeLevel = 1;
                break;
            case "Lightly Active": activeLevel = 2;
                break;
            case "Moderately Active": activeLevel = 3;
                break;
            case "Very Active": activeLevel = 4;
                break;
            case "Extra Active": activeLevel = 5;
                break;
            default: activeLevel = 0;
                break;
        }

        if(name != null && age != null && weight != null && height != null) {
            if(!name.equals("") && !age.equals( "") && !weight.equals("") && !height.equals( "")){
                user.setAll(name,Integer.parseInt(age),Integer.parseInt(height),Integer.parseInt(weight),gender,activeLevel);
                user.save();

                Output.toastMessage(this, "Changes updated.", Output.SHORT_TOAST);

                Intent gameMode = new Intent(this, MainActivity.class);
                startActivity(gameMode);
                finish();
            } else {
                Output.toastMessage(this, "Please fill in all fields", Output.SHORT_TOAST);
            }
        } else {
            Output.toastMessage(this, "Please fill in all fields", Output.SHORT_TOAST);
        }
    }
}