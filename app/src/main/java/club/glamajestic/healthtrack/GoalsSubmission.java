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

/**
 * <code>GoalsSubmission</code> is an <code>Activity</code> that allows users to create
 * health-related goals.
 */
public class GoalsSubmission extends Activity implements View.OnClickListener {

    private Button saveButton;
    private EditText editText;
    private TextView textView;
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_submission_page);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        editText = (EditText)findViewById(R.id.targetWeightTextEntry);
        textView = (TextView)findViewById(R.id.textView2);
        textView.setVisibility(View.GONE);
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
     * Creates a new <code>Intent</code> and starts <code>SettingsActivity</code>.
     *
     * @param view Unused.
     */
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
        Output.toastMessage(this, "Changes updated.", Output.SHORT_TOAST);
        saveText();
    }

    // Saves info in a string: "Target weight, Target num weeks to complete goal"
    private void saveText(){
        String message = editText.getText().toString();
        String fileName = "userGoal";
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName,MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            editText.setText("");

            editText = (EditText)findViewById(R.id.targetWeeksTextEntry);
            message = ", " + editText.getText().toString();
            fileOutputStream.write(message.getBytes());

            fileOutputStream.close();
            editText.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}