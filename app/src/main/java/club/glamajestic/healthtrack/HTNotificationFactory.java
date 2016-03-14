package club.glamajestic.healthtrack;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by scott on 11/03/16.
 */
public class HTNotificationFactory {
    public htNotification createNotification (String type, double alertAmount){
        //Context context = getActivity();
        //SharedPreferences sharedPref = context
        return new htNotification(type, alertAmount);

    }
}
