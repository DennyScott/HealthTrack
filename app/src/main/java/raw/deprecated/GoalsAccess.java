//package raw.deprecated;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.pm.PackageManager;
//import android.os.Environment;
//import android.support.v4.app.ActivityCompat;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
//import business.*;
//
//
//public class GoalsAccess implements Serializable, ApplicationConstants {
//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    Activity ctx;
//    business.Goals goals;
//
//    public GoalsAccess() {
//        File userGoals = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userGoals.ser");
//        if (userGoals.exists()) {
//            try {
//                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userGoals)));
//                goals = (business.Goals) ois.readObject();
//                ois.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            goals = new business.Goals();
//        }
//    }
//
//    public GoalsAccess(Activity ctx) {
//        this.ctx = ctx;
//        verifyStoragePermissions(ctx);
//        File userGoals = new File(Environment.getExternalStorageDirectory().getPath() + "/HealthTrack/userGoals.ser");
//        if (userGoals.exists()) {
//            try {
//                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userGoals)));
//                goals = (business.Goals) ois.readObject();
//                ois.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            goals = new business.Goals();
//        }
//    }
//
//    public static void verifyStoragePermissions(Activity activity) {
//
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }
//
//    public int getTargetWeight() {
//        if (goals.isSet()){
//            return goals.getTargetWeight();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetWeeks() {
//        if(goals.isSet()){
//            return goals.getTargetWeeks();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetFat() {
//        if(goals.isSet()){
//            return goals.getFat();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetCarbs() {
//        if(goals.isSet()){
//            return goals.getCarbs();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetProtein() {
//        if(goals.isSet()){
//            return goals.getProtein();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetSodium() {
//        if(goals.isSet()){
//            return goals.getSodium();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetPotassium() {
//        if(goals.isSet()){
//            return goals.getPotassium();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetFiber() {
//        if(goals.isSet()){
//            return goals.getFiber();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetIron() {
//        if(goals.isSet()){
//            return goals.getIron();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public int getTargetVitamin() {
//        if(goals.isSet()){
//            return goals.getVitamin();
//        }
//        else{
//            return -1;
//        }
//    }
//
//    public boolean isSet() {
//        return goals.isSet();
//    }
//
//    public void setAll(int targetWeight, int targetWeeks, int[] targetNutrients) {
//        if (goals ==null)
//            goals = new business.Goals();
//        goals.setTargetWeight(targetWeight);
//        goals.setTargetWeeks(targetWeeks);
//        goals.setFat(targetNutrients[0]);
//        goals.setCarbs(targetNutrients[1]);
//        goals.setProtein(targetNutrients[2]);
//        goals.setSodium(targetNutrients[3]);
//        goals.setPotassium(targetNutrients[4]);
//        goals.setFiber(targetNutrients[5]);
//        goals.setIron(targetNutrients[6]);
//        goals.setVitamin(targetNutrients[7]);
//        goals.setSet(true);
//    }
//
//    public void save() {
//        if (isSet()) {
//            verifyStoragePermissions(ctx);
//            try {
//                ObjectOutputStream oos = new ObjectOutputStream(
//                        new BufferedOutputStream(
//                                new FileOutputStream(
//                                        new File(
//                                                Environment.getExternalStorageDirectory().getPath() +
//                                                        "/HealthTrack/userGoals.ser"
//                                        )
//                                )
//                        )
//                );
//                oos.writeObject(goals);
//                oos.flush();
//                oos.close();
//            } catch (IOException r) {
//                r.printStackTrace();
//            }
//        }
//    }
//
//}
