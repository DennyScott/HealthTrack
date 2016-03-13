package business;

import java.io.Serializable;

/**
 * Created by Robby on 3/13/2016.
 */
class UserData implements Serializable, ApplicationConstants {
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
