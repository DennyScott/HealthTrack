package business;

import java.io.Serializable;

/**
 * Created by Robby on 3/13/2016.
 */
class Goals implements Serializable, ApplicationConstants {
    int targetWeight;
    int targetWeeks;
    boolean set;
    Goals(){
        set = false;
        targetWeight = 0;
        targetWeeks = 0;
    }
}
