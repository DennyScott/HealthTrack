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
}
