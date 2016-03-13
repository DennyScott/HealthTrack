package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import business.BMR;
import business.CaloriesPerDay;
import business.GoalsAccess;

/**
 * <code>GoalsSubmission</code> is an <code>Activity</code> that allows users to create
 * health-related goals.
 */
public class GoalsSubmission extends Activity implements View.OnClickListener {

    private Button saveButton;
    private EditText targetWeightText;
    private EditText targetWeeksText;
    private GoalsAccess goals;
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_submission_page);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        targetWeightText = (EditText)findViewById(R.id.targetWeightTextEntry);
        targetWeeksText = (EditText)findViewById(R.id.targetWeeksTextEntry);
        goals = new GoalsAccess(this);
    }

    public void onBackPressed() {
        Output.toastMessage(this, "Returning to previous screen.", Output.LONG_TOAST);
        finish();
    }

    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, SettingsActivity.class);
        startActivity(gameMode);
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

    private void saveText() {
        String targetWeight = targetWeightText.getText().toString();
        String targetWeeks = targetWeeksText.getText().toString();
        if (targetWeight!=null && targetWeeks!=null) {
            if (!targetWeight.equals("") && !targetWeeks.equals("")) {
                goals.setAll(Integer.parseInt(targetWeight),Integer.parseInt(targetWeeks));
                goals.save();
                Output.toastMessage(this, "Changes updated.", Output.SHORT_TOAST);
                Intent gameMode = new Intent(this, mainStats.class);
                startActivity(gameMode);
                finish();
            }
            else {
                Output.toastMessage(this, "Please fill in all fields", Output.SHORT_TOAST);
            }
        }
        else {
            Output.toastMessage(this, "Please fill in all fields", Output.SHORT_TOAST);
        }
    }
}