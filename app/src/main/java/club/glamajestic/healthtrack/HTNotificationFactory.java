package club.glamajestic.healthtrack;

import android.content.Context;
import android.content.SharedPreferences;


public class HTNotificationFactory {
    public htNotification createNotification (String type, double alertAmount){
        //Context context = getActivity();
        //SharedPreferences sharedPref = context
        return new htNotification(type, alertAmount);

    }
}
