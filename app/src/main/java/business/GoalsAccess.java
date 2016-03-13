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

/**
 * Created by Wilson on 3/12/2016.
 */
public class GoalsAccess implements Serializable {
    Activity ctx;
    Goals goals;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public GoalsAccess(){
        File userGoals = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userGoals.ser");
        if (userGoals.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userGoals)));
                goals = (Goals) ois.readObject();
                ois.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            goals = new Goals();
        }
    }
    public GoalsAccess(Activity ctx){
        this.ctx = ctx;
        verifyStoragePermissions(ctx);
        File userGoals = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userGoals.ser");
        if (userGoals.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userGoals)));
                goals = (Goals) ois.readObject();
                ois.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            goals = new Goals();
        }
    }
    public int getTargetWeight(){
        return goals.targetWeight;
    }
    public int getTargetWeeks(){
        return goals.targetWeeks;
    }

    public boolean isSet(){
        return goals.set;
    }
    public void setAll(int targetWeight ,int targetWeeks){
        goals.targetWeight = targetWeight;
        goals.targetWeeks = targetWeeks;
        goals.set = true;
    }
    public void save(){
        if(isSet()) {
            verifyStoragePermissions(ctx);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userGoals.ser"))));
                oos.writeObject(goals);
                oos.flush();
                oos.close();
            } catch (IOException r) {
                r.printStackTrace();
            }
        }
    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
class Goals implements Serializable{
    int targetWeight;
    int targetWeeks;
    boolean set;
    Goals(){
        set = false;
        targetWeight = 0;
        targetWeeks = 0;
    }
}