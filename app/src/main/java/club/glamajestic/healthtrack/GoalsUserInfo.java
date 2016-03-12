package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import business.UserDataAccess;

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
    private TextView display;
    private UserDataAccess user;
    private RadioButton male, female;
    int gender;

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
        display = (TextView)findViewById(R.id.textView);
        display.setVisibility(View.GONE);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        user = new UserDataAccess(this);
    }

    /**
     * Calls <code>finish()</code> to close this <code>Activity</code>, returning to previous
     * <code>Activity</code> on the stack.
     */
    public void onBackPressed() {
        Output.toastMessage(this, "Returning to previous screen.", Output.LONG_TOAST);
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
        Output.toastMessage(this, "Changes updated.", Output.SHORT_TOAST);
        saveText();
    }
    public void male(View view) {
        gender= 0;
    }
    public void female(View view) {
        gender= 1;
    }

    // Saves info in a string: "Full Name, age, weight"
    private void saveText(){
        String name = nameText.getText().toString();
        String age = ageText.getText().toString();
        String weight = weightText.getText().toString();
        String height = heightText.getText().toString();
        if(name != null && age != null && weight != null && height != null) {
            if(!name.equals("") && !age.equals( "") && !weight.equals("") && !height.equals( "")){
                user.setAll(name,Integer.parseInt(age),Integer.parseInt(height),Integer.parseInt(weight),gender);
                user.save();
                Intent gameMode = new Intent(this, mainStats.class);
                startActivity(gameMode);
                finish();
            }else{
                Output.toastMessage(this, "Please fill in all fields", Output.SHORT_TOAST);
            }
        }
        else{
            Output.toastMessage(this, "Please fill in all fields", Output.SHORT_TOAST);
        }


    }
}