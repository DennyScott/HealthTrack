//package raw.deprecated;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import club.glamajestic.healthtrack.Output;
//import club.glamajestic.healthtrack.R;
//import club.glamajestic.healthtrack.SettingsActivity;
//
///**
// * <code>Warnings</code> is an <code>Activity</code> that reports any user health risks.
// */
//public class Warnings extends Activity {
//
//    /**
//    * {@inheritDoc}
//    */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.warnings);
//    }
//
//    public void onBackPressed() {
//        Output.toastMessage(this, "Returning to previous screen.", Output.LONG_TOAST);
//        finish();
//    }
//
//    public void settingsButton(View view) {
//        Intent gameMode = new Intent(this, SettingsActivity.class);
//        startActivity(gameMode);
//    }
//}
