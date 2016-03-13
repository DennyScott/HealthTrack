package club.glamajestic.healthtrack;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import persistence.*;

/**
 * Handles and throws notifications to the user.
 *
 * @see NotificationCompat
 * @see NotificationManager
 */
public class htNotification {

    private String type;

    public htNotification(String type) {
        this.type = type;
    }

    public static void throwNotification(String type, int amount, Context context) {
        DatabaseDefinition datadef = new DatabaseDefinition(context,null,null,1,null);
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_popup_disk_full)
                .setContentTitle("HealthTrack")
                .setContentText("You have exceeded your limit for " + type + " of " + amount);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, notBuilder.build());
    }
}
