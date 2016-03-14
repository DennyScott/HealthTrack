package club.glamajestic.healthtrack;

import android.app.NotificationManager;
import android.content.Context;
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
public class htNotification {

    private String type;
    private double amount;

    public htNotification(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Throws the notification in the background to the user.
     *
     * @param type The nutritional type whose limit has been exceeded.
     * @param dateRange The amount that the type has been exceeded by.
     * @param context Allows access so the notification can be sent.
     */
    public void throwNotification(String type, ArrayList<Date> dateRange, Context context) {
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_popup_disk_full)
                .setContentTitle("HealthTrack")
                .setContentText("You have exceeded your limit for " + type + " of " + amount);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, notBuilder.build());
    }
}
