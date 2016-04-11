//package raw.deprecated;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import raw.deprecated.GoalsUserInfo;
//import club.glamajestic.healthtrack.R;
//import club.glamajestic.healthtrack.SettingsActivity;
//
///**
// * <code>Goals</code> is an <code>Activity</code> that allows users to set and manage goals
// * based on relevant user information.
// */
//public class Goals extends Activity implements View.OnClickListener {
//
//    private Button goalsUserInfoButton;
//    private Button goalsSubmissionButton;
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.goals);
//        goalsUserInfoButton = (Button)findViewById(R.id.goalsUserInfoButton);
//        goalsUserInfoButton.setOnClickListener(this);
//        goalsSubmissionButton = (Button)findViewById(R.id.goalsSubmissionButton);
//        goalsSubmissionButton.setOnClickListener(this);
//    }
//
//    public void onBackPressed() {
//        //Intent gameMode = new Intent(this, MainActivity.class);
//        //startActivity(gameMode);
//        finish();
//    }
//
//    public void settingsButton(View view) {
//        Intent gameMode = new Intent(this, SettingsActivity.class);
//        startActivity(gameMode);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.goalsUserInfoButton:
//                goalsUserInfoClick();
//                break;
//            case R.id.goalsSubmissionButton:
//                goalsUserSubmissionClick();
//                break;
//        }
//    }
//
//    private void goalsUserInfoClick() {
//        Intent gameMode = new Intent(this, GoalsUserInfo.class);
//        startActivity(gameMode);
//    }
//
//    private void goalsUserSubmissionClick() {
//        Intent gameMode = new Intent(this, GoalsSubmission.class);
//        startActivity(gameMode);
//    }
//}
