package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import business.CalcCaloriesPerDay;
import business.GoalsAccess;

/**
 * <code>GoalsSubmission</code> is an <code>Activity</code> that allows users to create
 * health-related goals.
 */
public class GoalsSubmission extends Activity implements View.OnClickListener {

    private Button saveButton;
    private EditText targetWeightText;
    private EditText targetWeeksText;
    private EditText fatText;
    private EditText carbsText;
    private EditText proteinText;
    private EditText sodiumText;
    private EditText potassiumText;
    private EditText fiberText;
    private EditText ironText;
    private EditText vitaminText;
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
        fatText = (EditText)findViewById(R.id.fatTextEntry);
        carbsText = (EditText)findViewById(R.id.carbsTextEntry);
        proteinText = (EditText)findViewById(R.id.proteinTextEntry);
        sodiumText = (EditText)findViewById(R.id.sodiumTextEntry);
        potassiumText = (EditText)findViewById(R.id.potassiumTextEntry);
        fiberText = (EditText)findViewById(R.id.fiberTextEntry);
        ironText = (EditText)findViewById(R.id.ironTextEntry);
        vitaminText = (EditText)findViewById(R.id.vitaminTextEntry);
        goals = new GoalsAccess(this);
    }

    public void onBackPressed() {

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

                // indices 0-7 inclusive are respectively fat, carbs, protein, sodium, potassium, fiber, iron, vitamin
                int[] targetNutrients = nutrientsToInt();

                goals.setAll(Integer.parseInt(targetWeight),Integer.parseInt(targetWeeks), targetNutrients);
                goals.save();
                Output.toastMessage(this, "Changes updated.", Output.SHORT_TOAST);
                Intent gameMode = new Intent(this, MainActivity.class);
                startActivity(gameMode);
                finish();
            }
            else {
                Output.toastMessage(this, "Please fill in all required fields", Output.SHORT_TOAST);
            }
        }
        else {
            Output.toastMessage(this, "Please fill in all required fields", Output.SHORT_TOAST);
        }
    }

    private int[] nutrientsToInt() {
        String fat = fatText.getText().toString();
        String carbs = carbsText.getText().toString();
        String protein = proteinText.getText().toString();
        String sodium = sodiumText.getText().toString();
        String potassium = potassiumText.getText().toString();
        String fiber = fiberText.getText().toString();
        String iron = ironText.getText().toString();
        String vitamin = vitaminText.getText().toString();
        int[] nutrients = new int[8];

        if (fat.equals(""))
            nutrients[0] = 0;
        else
            nutrients[0] = Integer.parseInt(fat);

        if (carbs.equals(""))
            nutrients[1] = 0;
        else
            nutrients[1] = Integer.parseInt(carbs);

        if (protein.equals(""))
            nutrients[2] = 0;
        else
            nutrients[2] = Integer.parseInt(protein);

        if (sodium.equals(""))
            nutrients[3] = 0;
        else
            nutrients[3] = Integer.parseInt(sodium);

        if (potassium.equals(""))
            nutrients[4] = 0;
        else
            nutrients[4] = Integer.parseInt(potassium);

        if (fiber.equals(""))
            nutrients[5] = 0;
        else
            nutrients[5] = Integer.parseInt(fiber);

        if (iron.equals(""))
            nutrients[6] = 0;
        else
            nutrients[6] = Integer.parseInt(iron);

        if (vitamin.equals(""))
            nutrients[7] = 0;
        else
            nutrients[7] = Integer.parseInt(vitamin);

        return nutrients;
    }
}