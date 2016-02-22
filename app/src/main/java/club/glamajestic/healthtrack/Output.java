package club.glamajestic.healthtrack;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class Output {
    public static final int SHORT_TOAST = 0;
    public static final int LONG_TOAST = 1;

    public static void toastMessage(Context context, String message, int length) {
        Toast info = new Toast(context);
        info.makeText(context, message, length).show();
    }
}
