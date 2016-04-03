package business;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class GoalsAccess implements Serializable, ApplicationConstants {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Activity ctx;
    Goals goals;

    public GoalsAccess() {
        File userGoals = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userGoals.ser");
        if (userGoals.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userGoals)));
                goals = (Goals) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            goals = new Goals();
        }
    }

    public GoalsAccess(Activity ctx) {
        this.ctx = ctx;
        verifyStoragePermissions(ctx);
        File userGoals = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userGoals.ser");
        if (userGoals.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userGoals)));
                goals = (Goals) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            goals = new Goals();
        }
    }

    public static void verifyStoragePermissions(Activity activity) {

        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public int getTargetWeight() {
        if (goals.isSet()){
            return goals.getTargetWeight();
        }
        else{
            return -1;
        }
    }

    public int getTargetWeeks() {
        if(goals.isSet()){
            return goals.getTargetWeeks();
        }
        else{
            return -1;
        }
    }

    public boolean isSet() {
        return goals.isSet();
    }

    public void setAll(int targetWeight, int targetWeeks) {
        if (goals==null)
            goals = new Goals();
        goals.setTargetWeight(targetWeight);
        goals.setTargetWeeks(targetWeeks);
        goals.setSet(true);
    }

    public void save() {
        if (isSet()) {
            verifyStoragePermissions(ctx);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(
                                        new File(
                                                Environment.getExternalStorageDirectory().getPath() +
                                                        "/HealthTrack/userGoals.ser"
                                        )
                                )
                        )
                );
                oos.writeObject(goals);
                oos.flush();
                oos.close();
            } catch (IOException r) {
                r.printStackTrace();
            }
        }
    }

}
