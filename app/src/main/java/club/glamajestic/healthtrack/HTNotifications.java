package club.glamajestic.healthtrack;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.sql.Date;
import java.util.ArrayList;

import persistence.*;

/**
 * Handles and throws notifications to the user.
 *
 * @see NotificationCompat
 * @see NotificationManager
 */
public class HTNotifications {

    private String type;
    private double amount;
    private String message;
    public HTNotifications(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public HTNotifications(String message) {
        this.message = message;
    }
    /**
     * Throws the notification in the background to the user.
     *
     * @param context Allows access so the notification can be sent.
     */
    public void throwNotification(Context context) {
        NotificationCompat.Builder notBuilder = null;
        if (message.equals("")) {
            notBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(android.R.drawable.ic_popup_disk_full)
                    .setContentTitle("HealthTrack")
                    .setContentText("You have exceeded your limit for " + type + " of " + amount);
        } else {
            notBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("HealthTrack")
                    .setContentText(message);
        }
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.

        mNotificationManager.notify(1, notBuilder.build());
    }
}
