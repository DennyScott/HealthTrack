package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Goals extends Activity implements View.OnClickListener {
    private Button goalsUserInfoButton;
    private Button goalsSubmissionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);
        goalsUserInfoButton = (Button)findViewById(R.id.goalsUserInfoButton);
        goalsUserInfoButton.setOnClickListener(this);
        goalsSubmissionButton = (Button)findViewById(R.id.goalsSubmissionButton);
        goalsSubmissionButton.setOnClickListener(this);
    }

    public void onBackPressed() {
        Output.toastMessage(this, "Returning to previous screen.", Output.LONG_TOAST);
        finish();
    }

    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, Settings.class);
        startActivity(gameMode);
    }

    private void goalsUserInfoClick() {
        Intent gameMode = new Intent(this, GoalsUserInfo.class);
        startActivity(gameMode);
    }

    private void goalsUserSubmissionClick() {
        Intent gameMode = new Intent(this, GoalsSubmission.class);
        startActivity(gameMode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goalsUserInfoButton:
                goalsUserInfoClick();
                break;
            case R.id.goalsSubmissionButton:
                goalsUserSubmissionClick();
                break;
        }
    }
}
