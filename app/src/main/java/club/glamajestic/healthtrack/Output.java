package club.glamajestic.healthtrack;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * <code>Output</code> holds methods that can be called to display various kinds of
 * output messages. The purpose of the class is to make code reusable.
 */
public class Output {

    public static final int SHORT_TOAST = 0;

    public static final int LONG_TOAST = 1;

    public static void toastMessage(Context context, String message, int length) {
        if(length != Toast.LENGTH_SHORT && length != Toast.LENGTH_LONG) {
            length = Toast.LENGTH_SHORT;
        }

        Toast info = new Toast(context);
        Toast.makeText(context, message, length).show();
    }
}
