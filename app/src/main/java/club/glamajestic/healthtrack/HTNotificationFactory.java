package club.glamajestic.healthtrack;


public class HTNotificationFactory {
    public HTNotifications createNotification (String type, double alertAmount){
        //Context context = getActivity();
        //SharedPreferences sharedPref = context
        return new HTNotifications(type, alertAmount);

    }
}
