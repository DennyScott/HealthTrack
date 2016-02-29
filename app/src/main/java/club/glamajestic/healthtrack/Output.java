package club.glamajestic.healthtrack;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * <code>Output</code> holds methods that can be called to display various kinds of
 * output messages. The purpose of the class is to make code reusable.
 */
public class Output {

    /**
     * Shows <code>Toast</code> message for a short time period.
     * @see Toast
     */
    public static final int SHORT_TOAST = 0;

    /**
     * Shows <code>Toast</code> message for a long time period.
     * @see Toast
     */
    public static final int LONG_TOAST = 1;

    /**
     *
     * @param context The <code>Context</code> used. Usually an <code>Application</code> or
     *                <code>Activity</code>.
     * @param message The message that will be shown.
     * @param length How long to show the message.
     * @see Toast
     */
    public static void toastMessage(Context context, String message, int length) {
        if(length != Toast.LENGTH_SHORT && length != Toast.LENGTH_LONG) {
            length = Toast.LENGTH_SHORT;
        }

        Toast info = new Toast(context);
        info.makeText(context, message, length).show();
    }
}
