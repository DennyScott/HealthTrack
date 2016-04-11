package club.glamajestic.healthtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import business.UserDataAccess;
import persistence.DatabaseDefinition;


public class Splash extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        UserDataAccess.verifyStoragePermissions(this);
        if(DatabaseDefinition.currentDatabase == null){
            DatabaseDefinition df  = new DatabaseDefinition(this);
        }
        final ImageView splashPic = (ImageView)findViewById(R.id.splashPic);
        final TextView splashText = (TextView) findViewById(R.id.splashText);
        Animation rot = AnimationUtils.loadAnimation(this,R.anim.rotate);
        final Animation fdOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        final Animation fdIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);

        splashText.startAnimation(fdIn);
        splashPic.startAnimation(rot);
        rot.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splashPic.startAnimation(fdOut);
                splashText.startAnimation(fdOut);
                startApp();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    public void startApp() {
        try {
            File dir = new File(Environment.getExternalStorageDirectory().getPath(), "HealthTrack");
            if(!dir.exists()){
                dir.mkdir();
            }

            File userInfo = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userInfo.ser");
            if (userInfo.exists()) {
                Intent gameMode = new Intent(this, MainActivity.class);
                startActivity(gameMode);
            } else {
                Intent gameMode = new Intent(this, GoalsUserInfo.class);
                startActivity(gameMode);
            }

            finish();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}