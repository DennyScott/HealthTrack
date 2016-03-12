package business;

/**
 * Created by Wilson on 3/11/2016.
 */

/*
TO DO: save user info into this business object rather than an internal storage file.
Ideally in static variables so that information is loaded up immediately.
** Having some trouble with this **
 */
public class UserInfo {
    static String name;
    static int age;
    static double weight; // in pounds
    static int height; // in inches
    static boolean male; // true is male, false is female

    public static String getName() {
        return name;
    }

    public static int getAge() {
        return age;
    }

    public static double getWeight() {
        return weight;
    }

    public static int getHeight() {
        return height;
    }

    public static boolean isMale() {
        return male;
    }

    public static void setName(String name) {
        UserInfo.name = name;
    }

    public static void setAge(int age) {
        UserInfo.age = age;
    }

    public static void setWeight(double weight) {
        UserInfo.weight = weight;
    }

    public static void setHeight(int height) {
        UserInfo.height = height;
    }

    public static void setMale(boolean valid) {
        UserInfo.male = valid;
    }
}
