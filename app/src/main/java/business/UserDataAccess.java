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


public class UserDataAccess implements Serializable, ApplicationConstants {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Activity ctx;
    UserData user;

    public UserDataAccess() {
        File userInfo = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userInfo.ser");
        if (userInfo.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userInfo)));
                user = (UserData) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            user = new UserData();
        }
    }

    public UserDataAccess(Activity ctx) {
        this.ctx = ctx;
        verifyStoragePermissions(ctx);
        File userInfo = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userInfo.ser");
        if (userInfo.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userInfo)));
                user = (UserData) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            user = new UserData();
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

    public String getName() {
        return user.getName();
    }

    public int getGender() {
        return user.getGender();
    }

    public int getAge() {
        return user.getAge();
    }

    public int getWeight() {
        return user.getWeight();
    }

    public int getHeight() {
        return user.getHeight();
    }

    public int getActiveLevel() {
        return user.getActiveLevel();
    }

    public boolean isSet() {
        return user.isSet();
    }

    public void setAll(String name, int age, int height, int weight, int gender, int activeLevel) {
        if (user == null) user = new UserData();
        user.setName(name);
        user.setAge(age);
        user.setHeight(height);
        user.setWeight(weight);
        user.setGender(gender);
        user.setActiveLevel(activeLevel);
        user.setSet(true);
    }

    public void save() {
        if (isSet()) {
            verifyStoragePermissions(ctx);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userInfo.ser"))));
                oos.writeObject(user);
                oos.flush();
                oos.close();
            } catch (IOException r) {
                r.printStackTrace();
            }
        }
    }

}
