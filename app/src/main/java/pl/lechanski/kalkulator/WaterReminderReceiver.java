package pl.lechanski.kalkulator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WaterReminderReceiver extends BroadcastReceiver {
    static int waterCounter = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        int currentHour = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (currentHour > 6 && currentHour < 22) {
            System.out.println("ZALOGOWANY ALARM WODA");

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "woda")
                    .setSmallIcon(R.drawable.bmi)
                    .setContentTitle("Przypomnienie")
                    .setContentText("Napij się wody")
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(waterCounter++, builder.build());
        } else {
            System.out.println("Powiadomienie o wodzie pominiete - noc");
        }
    }
}