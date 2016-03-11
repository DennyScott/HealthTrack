package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * <code>GoalsUserInfo</code> allows the user to enter information about themselves, which the
 * app can then use when providing statistics and managing goals.
 */
public class GoalsUserInfo extends Activity implements View.OnClickListener {

    private Button saveButton;
    private EditText editText;
    private TextView textView;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_user_info);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        editText = (EditText)findViewById(R.id.nameTextEntry);
        textView = (TextView)findViewById(R.id.textView);
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

    // Saves info in a string: "Full Name, age, weight"
    private void saveText(){
        String message = editText.getText().toString();
        String fileName = "userInfo";
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName,MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            editText.setText("");

            editText = (EditText)findViewById(R.id.ageTextEntry);
            message = ", " + editText.getText().toString();
            fileOutputStream.write(message.getBytes());
            editText.setText("");

            editText = (EditText)findViewById(R.id.weightTextEntry);
            message = ", " + editText.getText().toString();
            fileOutputStream.write(message.getBytes());
            editText.setText("");

            editText = (EditText)findViewById(R.id.heightTextEntry);
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

    /*
    This is only here as a reminder of how to retrieve the user's input from internal storage,
    and that it works. It's temporary.
    Requires plain text view widget and a load button.
     */
    public void readMessage(View view) {
        try {
            String message;
            FileInputStream fileInputStream = openFileInput("userInfo");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((message=bufferedReader.readLine())!=null) {
                stringBuffer.append(message + "\n");
            }
            textView.setText(stringBuffer.toString());
            textView.setVisibility(View.VISIBLE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}