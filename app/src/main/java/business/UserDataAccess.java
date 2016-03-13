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
 * Created by Khaled on 3/11/2016.
 */
public class UserDataAccess implements Serializable {
    Activity ctx;
    UserData user;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public UserDataAccess(){
        File userInfo = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userInfo.ser");
        if (userInfo.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userInfo)));
                user = (UserData) ois.readObject();
                ois.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            user = new UserData();
        }
    }
    public UserDataAccess(Activity ctx){
        this.ctx = ctx;
        verifyStoragePermissions(ctx);
        File userInfo = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userInfo.ser");
        if (userInfo.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userInfo)));
                user = (UserData) ois.readObject();
                ois.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            user = new UserData();
        }
    }
    public String getName(){
        return user.name;
    }
    public int getGender(){
        return user.gender;
    }
    public int getAge(){
        return user.age;
    }
    public int getWeight(){
        return user.weight;
    }
    public int getHeight(){
        return user.height;
    }
    public boolean isSet(){
        return user.set;
    }
    public void setAll(String name, int age, int height, int weight,int gender){
        user.name = name;
        user.age = age;
        user.height = height;
        user.weight = weight;
        user.gender = gender;
        user.set = true;
    }
    public void save(){
        if(isSet()) {
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

}
class UserData implements Serializable{
    String name;
    int age;
    int weight;
    int height;
    int gender;//0 male, 1 female
    boolean set;
    UserData(){
        set = false;
        name = "";
        age = 0;
        weight=0;
        height=0;
        gender=1;
    }
}