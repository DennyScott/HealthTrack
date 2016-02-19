package club.glamajestic.healthtrack;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Deny Raaen on 04/02/2016.
 */
public class Output {
    public static void toastMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
