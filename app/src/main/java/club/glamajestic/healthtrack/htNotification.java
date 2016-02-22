package club.glamajestic.healthtrack;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import persistence.*;

/**
 * Created by scott on 29/01/16.
 * Notification handling object
 */
public class htNotification {
    Context contPassed;
    String type;

    public htNotification(String type){
        this.type = type;
    }

    public static void throwNotification(String type, int amount, Context cont) {
        DatabaseDefinition datadef = new DatabaseDefinition(cont,null,null,1,null);
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(cont)
                .setSmallIcon(android.R.drawable.ic_popup_disk_full)
                .setContentTitle("HealthTrack")
                .setContentText("You have exceeded your limit for "+type+" of "+amount);
        NotificationManager mNotificationManager =
                (NotificationManager) cont.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, notBuilder.build());
    }

}
