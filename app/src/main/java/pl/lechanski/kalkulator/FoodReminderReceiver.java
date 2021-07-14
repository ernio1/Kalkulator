package pl.lechanski.kalkulator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FoodReminderReceiver extends BroadcastReceiver {
    static int foodCounter = Integer.MAX_VALUE / 2;

    @Override
    public void onReceive(Context context, Intent intent) {
        int currentHour = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (currentHour > 6 && currentHour < 22) {
            System.out.println("ZALOGOWANY ALARM POSILEK");

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "posilek")
                    .setSmallIcon(R.drawable.bmi)
                    .setContentTitle("Przypomnienie")
                    .setContentText("Zjedz posiłek")
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(foodCounter++, builder.build());
        } else {
            System.out.println("Powiadomienie o posiłku pominiete - noc");
        }
    }
}